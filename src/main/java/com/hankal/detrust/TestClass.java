package com.hankal.detrust;


import com.google.gson.Gson;
import com.hankal.miaosha.vo.LoginVo;

public class TestClass {
    public static void main(String[] args) {
        convertObject();
    }

    public static void convertObject() {

        LoginVo stu = new LoginVo();
        stu.setMobile("13811413171");
        stu.setPassword("1111111111111111");

        Gson gs = new Gson();

        String json = gs.toJson(stu);   //先将javaBean转换成json
        System.out.println(json);

        LoginVo xiaoming = gs.fromJson(json, LoginVo.class);  //再将json转换成javaBean
        System.out.println(xiaoming);
    }
}
