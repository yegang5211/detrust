package com.hankal.detrust.service.loginLogic;

import com.hankal.detrust.domain.Device;
import com.hankal.detrust.domain.Login;
import com.hankal.detrust.domain.User;
import com.hankal.detrust.vo.UserContext;
import com.hankal.detrust.vo.RegisterVo;

import java.util.Date;

public class LoginBuilder {

    public User buildUser(RegisterVo registerVo, String userId) {

        User user = new User();
        user.setUserId(userId);
        user.setUserName(registerVo.getMobilePhone());
        user.setNickname(registerVo.getMobilePhone());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        return user;
    }

    public Login buildLogin(RegisterVo registerVo, String userId,
                            String loginId, String loginToken) {

        Login login = new Login();
        login.setLoginId(loginId);
        login.setUserId(userId);
        login.setPhone(registerVo.getMobilePhone());
        login.setPassword(registerVo.getPassword());
        login.setStatus(0); // 设置登录成功
        login.setEnterTime(new Date());
        login.setUpdateTime(new Date());
        login.setLoginToken(loginToken);

        return login;
    }

    public Device buildDevice(RegisterVo registerVo, String userId) {

        Device device = new Device();
        device.setDeviceId(registerVo.getDeviceId());
        device.setUserId(userId);
        device.setDeviceCode(0); // 设置设备为手机端
        device.setPhone(registerVo.getMobilePhone());
        device.setDeviceParams(registerVo.getDeviceParams());
        device.setActive(true);
        device.setActiveTime(new Date());

        return device;
    }

    public UserContext buildContext(String userId, String loginId,
                                    String deviceId, String mobilePhone,
                                    String loginToken) {

        UserContext vo = new UserContext();
        vo.setUserId(userId);
        vo.setLoginId(loginId);
        vo.setDeviceId(deviceId);
        vo.setMobilePhone(mobilePhone);
        vo.setLoginToken(loginToken);
        return vo;
    }
}
