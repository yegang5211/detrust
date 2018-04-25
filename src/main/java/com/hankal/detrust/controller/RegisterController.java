package com.hankal.detrust.controller;

import com.hankal.detrust.exception.GlobalException;
import com.hankal.detrust.result.CodeMsg;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.RegisterService;
import com.hankal.detrust.vo.RegisterVo;
import com.hankal.detrust.vo.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Api("注册管理")
public class RegisterController extends BaseController {
    @Autowired
    RegisterService registerService;

    /*
    发送验证码请求
     */
    @RequestMapping(value = "/{mobilePhone}/verifycode",
            method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【未实现】通过手机号注册要求先发送一个验证码", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<Boolean> sendVerifyCode(@ApiParam(required = true, name = "mobilePhone", value = "手机号码")
                                          @PathVariable String mobilePhone) {
        log.info("发送验证码");
        boolean resut = registerService.sendVerifyCode(mobilePhone);
        return Result.success(resut);
    }

    /*
    用户注册
     */
    @PostMapping(value = "/register", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【已实现】向服务器提交注册用户请求",
            httpMethod = "POST", notes = "客户端必须提供手机号码、验证码、登录密码等信息")
    @ResponseBody
    public Result<UserContext> registerUser(@RequestBody @Valid RegisterVo registerVo) {
        // 检查手机号是否已经注册了
        if (registerService.exist(registerVo.getMobilePhone()))
            throw new GlobalException(CodeMsg.MOBILE_HAVE_REGISTERED);

        // 检查验证码是否存在
        if (!registerService.checkVerifyCode(registerVo.getVerifyCode()))
            throw new GlobalException(CodeMsg.MOBILE_VERIFYCODE_NOT_MATCHWITH_DATABASE);

        return Result.success(registerService.register(registerVo));
    }
}
