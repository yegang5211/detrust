package com.hankal.detrust.controller;

import com.hankal.detrust.Util.UUIDUtil;
import com.hankal.detrust.domain.UserInfo;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.UserService;
import com.hankal.detrust.vo.ChangePhoneNumberVo;
import com.hankal.detrust.vo.ChangePwdVo;
import com.hankal.detrust.vo.RequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api("用户管理，包括变更密码、变更手机号")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    /*
    变更用户登录密码
     */
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ApiOperation(value = "【未实现】修改用户的密码，如果用户有多个手机", httpMethod = "POST", notes = "")
    @ResponseBody
    public Result<Boolean> changePassword(@RequestBody ChangePwdVo changePwdVo) {
        log.info("变更登录密码");
        return Result.success(true);
    }

    /*
    变更用户手机号码
     */
    @RequestMapping(value = "/user/mobilePhone", method = RequestMethod.POST)
    @ApiOperation(value = "【未实现】变更用户手机号",
            httpMethod = "POST", notes = "客户端必须提供旧密码和新密码")
    @ResponseBody
    public Result<Boolean> changeMobilePhone(@RequestBody ChangePhoneNumberVo changePhoneNumberVo) {
        log.info("变更用户手机号码");
        return Result.success(true);
    }

    /*
     查看所有用户的列表
    */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取所有的用户", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<UserInfo>> listAllUsers(RequestVo requestVo) {
        List<UserInfo> list = userService.getUsers();
        log.info("获取所有的用户");
        return Result.success(list);
    }

    /*
     根据手机号查询用户详情
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ApiOperation(value = "【未实现】根据手机号查询用户详情", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<UserInfo> getUserByMobilePhole(RequestVo baseVo) {
        UserInfo info = new UserInfo();
        info.setUserId(UUIDUtil.uuid());
        return Result.success(info);
    }
}
