package com.hankal.detrust.service.loginLogic;

import com.hankal.detrust.dao.LoginDao;
import com.hankal.detrust.dao.UserDao;
import com.hankal.detrust.domain.Device;
import com.hankal.detrust.domain.Login;
import com.hankal.detrust.vo.LoginVo;
import com.hankal.detrust.vo.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginDao loginDao;
    @Autowired
    UserDao userDao;

    public List<Login> getAll() {
        return loginDao.getAll();
    }

    public List<Login> getByMobilePhone(String mobilePhone) {
        return loginDao.getByMobilePhone(mobilePhone);
    }

    public String existDeviceId(String deviceId) {
        return loginDao.existDeviceId(deviceId);
    }

    public boolean isActiveAndLogined(LoginVo loginVo) {
        boolean isDeviceActived = isDeviceActived(loginVo.getMobilePhone(), loginVo.getDeviceId());
        boolean isLogined = isLogined(loginVo.getMobilePhone());
        if (isDeviceActived && isLogined) {
            // 要求已经登录的，重新生成token，而不是抛已经登录的错误

            return true;
        }

        return false;
    }

    public boolean createOrUpdateDevice(LoginVo loginVo, String accessToken) {
        boolean existDevice = true;
        String deviceId = existDeviceId(loginVo.getDeviceId());
        if (deviceId == null)
            existDevice = false;

        if (existDevice) {
            activeDevice(loginVo.getDeviceId(), loginVo.getMobilePhone(), true);
        } else {
            // 为手机号增加一个deviceId，并激活
            insertNewDevice(loginVo.getMobilePhone(), loginVo.getDeviceParams(),
                    loginVo.getDeviceId(), loginVo.getUserId());
        }

        // 反激活旧的手机设备
        unActiveDevice(loginVo.getDeviceId(), loginVo.getMobilePhone());

        // 更新登录状态为 0
        loginDao.updateLogin(loginVo.getMobilePhone(),
                0, // 0表示登录成功
                new Date(),
                accessToken,
                loginVo.getUserId());

        // TODO:发消息通知其他手机设备，强制下线  //

        return true;
    }

    public UserContext getUserContext(String mobilePhone, String password) {
        UserContext result = userDao.getUserContext(mobilePhone, password);
        return result;
    }

    public boolean isLogined(String mobilePhone) {
        Object logined = loginDao.IsLogined(mobilePhone);
        if (logined != null)
            return true;

        return false;
    }

    public boolean isDeviceActived(String mobilePhone, String deviceId) {
        // 检查手机号的设备是激活状态
        Object actived = loginDao.IsDeviceActived(mobilePhone, deviceId);
        if (actived != null)
            return true;

        return false;
    }

    private String insertNewDevice(String mobilePhone, String deviceParams, String deviceId, String userId) {
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setUserId(userId);
        device.setDeviceCode(0);
        device.setPhone(mobilePhone);
        device.setDeviceParams(deviceParams);
        device.setActive(true);
        device.setActiveTime(new Date());

        loginDao.insertNewDeviceId(device);
        return device.getDeviceId();
    }

    private boolean activeDevice(String deviceId, String mobilePhone, boolean requireStatus) {
        Date activeTime = new Date();
        loginDao.activeDevice(deviceId, mobilePhone, activeTime, requireStatus);
        return true;
    }

    private boolean unActiveDevice(String deviceId, String mobilePhone) {
        Date activeTime = new Date();
        loginDao.unActiveOtherDevice(deviceId, mobilePhone, activeTime);
        return true;
    }
}