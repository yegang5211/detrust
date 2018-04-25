package com.hankal.detrust.service;

import com.hankal.detrust.Util.UUIDUtil;
import com.hankal.detrust.dao.RegisterDao;
import com.hankal.detrust.domain.Device;
import com.hankal.detrust.domain.Login;
import com.hankal.detrust.domain.User;
import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.service.loginLogic.LoginBuilder;
import com.hankal.detrust.vo.RegisterVo;
import com.hankal.detrust.vo.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    RegisterDao registerDao;

    public boolean sendVerifyCode(String mobilePhone) {
        // 第一步：随机生成一个6位的数字，发送到redis，mobilePhone作为主键，有效期默认 60 秒
        // 第二步：调用短信发送接口，将短信发给客户手机
        return true; // 表示发送成功
    }

    public UserContext register(RegisterVo registerVo) {
        LoginBuilder loginBuilder = new LoginBuilder();
        String userId = UUIDUtil.uuid();
        String loginId = UUIDUtil.uuid();
        String loginToken = TokenGenerater.generate(userId);

        // 插入数据表
        insert(
                loginBuilder.buildUser(registerVo, userId),
                loginBuilder.buildLogin(registerVo, userId, loginId, loginToken),
                loginBuilder.buildDevice(registerVo, userId));

        return loginBuilder.buildContext(
                userId, loginId, registerVo.getDeviceId(), registerVo.getMobilePhone(), loginToken);
    }

    public boolean checkVerifyCode(String mobilePhone) {
        return true;
    }

    @Transactional
    boolean insert(User user, Login login, Device device) {
        try {
            registerDao.insertUser(user);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.USER_INSERT_ERROR);
        }

        try {
            registerDao.insertUserDevice(device);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.USER_DEVICE_INSERT_ERROR);
        }

        try {
            registerDao.insertUserRole(user.getUserId(), 2); // 2 是钱包用户
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.USER_ROLE_INSERT_ERROR);
        }

        try {
            registerDao.insertUserLogin(login);
        } catch (Exception ex) {
            throw new GlobalException(CodeMsg.USER_LOGIN_INSERT_ERROR);
        }

        return true; //表示注册成功
    }

    public boolean exist(String mobilePhone) {
        Object row = registerDao.getDeviceByPhone(mobilePhone);
        return row != null;
    }
}
