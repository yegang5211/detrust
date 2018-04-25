package com.hankal.detrust.service.loginLogic;

import com.hankal.detrust.dao.LogoutDao;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.vo.LogoutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogoutService {
    @Autowired
    LogoutDao logoutDao;

    public boolean IsLogouted(String mobilePhone) {
        Object result = logoutDao.IsLogouted(mobilePhone);
        return result != null; // 如果查到 有状态属于 -1 退出，-2登录失败，-3踢出 任何一个，则表示已经logout了
    }

    public Result<Boolean> logout(LogoutVo logoutVo) {
        // 更新 user_login表的退出状态
        logoutDao.logout(-1, logoutVo.getMobilePhone(), new Date());
        return Result.success(true);
    }
}
