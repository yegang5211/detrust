package com.hankal.detrust.controller;

import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.loginLogic.LogoutService;
import com.hankal.detrust.vo.LogoutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api("登录管理-登出系统")
public class LogoutController extends BaseController {

    @Autowired
    LogoutService logoutService;

    /*
  退出登录
   */
    @PostMapping(value = "/logout", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【已实现】向服务器提交退出登录请求, 手机号和设备标识必须提供，以便于识别用户使用哪个手机设备登录",
            httpMethod = "POST", notes = "客户端必须提供手机号码、手机设备标识等信息")
    @ResponseBody
    public Result<Boolean> doLogout(@RequestBody LogoutVo logoutVo) {
        // 检查和设置参数
        if (logoutVo.getDeviceId() == null || logoutVo.getDeviceId() == "")
            return Result.error(CodeMsg.DEVICEID_NOTSURPPORT);

        boolean isLogouted = logoutService.IsLogouted(logoutVo.getMobilePhone());
        if (isLogouted)
            return Result.error(CodeMsg.MOBILE_HAVE_LOGOUT);

        log.info("退出登录");

        return logoutService.logout(logoutVo);
    }

}
