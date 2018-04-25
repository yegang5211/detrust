package com.hankal.detrust.controller;

import com.hankal.detrust.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/log")
@Api("所有业务请求全部调用该接口，存储到服务器") //TODO：【swagger】
public class LogController extends BaseController {
    @ApiOperation(value = "使用redit存储所有业务请求的消息数据", notes = "发送业务请求日志", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> logUser() {
        return Result.success("Hello，world");
    }

    @RequestMapping(value = "/wallet", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> logWallet() {
        return Result.success("Hello，world");
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> logTransaction() {
        return Result.success("Hello，world");
    }
}
