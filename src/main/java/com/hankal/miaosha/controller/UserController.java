package com.hankal.miaosha.controller;

import com.hankal.miaosha.domain.MiaoshaUser;
import com.hankal.detrust.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yegang5211 on 2018/2/9.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser miaoshaUser) {
        return Result.success(miaoshaUser);
    }
}
