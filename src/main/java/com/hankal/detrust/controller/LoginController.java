package com.hankal.detrust.controller;

import com.hankal.detrust.domain.Login;
import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.TokenGenerater;
import com.hankal.detrust.service.loginLogic.LoginService;
import com.hankal.detrust.vo.LoginVo;
import com.hankal.detrust.vo.QueryAllLogins;
import com.hankal.detrust.vo.QueryLoginByPhone;
import com.hankal.detrust.vo.UserContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Api("登录管理-登录进入系统")
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    /*
    登录
     */
    @PostMapping(value = "/login", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【已实现】向服务器提交登录请求, deviceId必须提供，以便于识别用户使用哪个手机设备登录",
            httpMethod = "POST", notes = "客户端必须提供手机号码、登录密码、手机设备标识等信息")
    @ResponseBody
    public Result<UserContext> login(@RequestBody @Valid LoginVo loginVo) {
        log.info("检查手机号和密码是否在数据库中存在");

        UserContext userConstext = loginService.getUserContext(
                loginVo.getMobilePhone(), loginVo.getPassword());
        if (userConstext == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST_OR_PASSWORD_ERROR);

        // 更新从数据库获得登录账号的userId
        loginVo.setUserId(userConstext.getUserId());

        userConstext.setDeviceId(loginVo.getDeviceId());
        userConstext.setLoginToken(TokenGenerater.generate(loginVo.getUserId()));

        boolean isActiveAndLogined = loginService.isActiveAndLogined(loginVo);
        if (isActiveAndLogined) {
            return Result.success(userConstext);
        }

        loginService.createOrUpdateDevice(loginVo, userConstext.getLoginToken());
        return Result.success(userConstext);
    }

    /*
     查看所有登录信息
     */
    @RequestMapping(value = "/logins", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取所有的登录信息，没有被激活的手机设备不显示", httpMethod = "GET", notes = "")
    @ResponseBody
    @Required
    public Result<List<Login>> listAllLogins(QueryAllLogins queryAllLogins) {
        List<Login> list = loginService.getAll();
        log.info("查看所有登录信息 共查询到" + list.size());
        return Result.success(list);
    }

    /*
    根据手机号获取用户的登录信息
     */
    @RequestMapping(value = "/login/{mobilePhone}/info", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】根据手机号获取用户的登录信息，包括没有被激活的手机设备", httpMethod = "GET", notes = "")
    @ResponseBody
    public Result<List<Login>> getLoginByMobilePhone(QueryLoginByPhone queryLoginByPhone) {
        List<Login> list = loginService.getByMobilePhone(queryLoginByPhone.getMobilePhone());
        return Result.success(list);
    }
}
