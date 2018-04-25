package com.hankal.detrust.result;

/**
 * Created by yegang5211 on 2018/2/9.
 */
public class CodeMsg {

    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "非法请求（path失效）");
    public static CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500104, "访问太频繁！");

    //登录模块 5002XX

    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");

    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg MOBILE_HAVE_REGISTERED = new CodeMsg(500215, "手机号已经被注册过了");

    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500216, "密码错误");
    public static CodeMsg MOBILE_NOT_EXIST_OR_PASSWORD_ERROR = new CodeMsg(500216, "手机号不存在或密码错误");

    public static CodeMsg MOBILE_HAVE_LOGINED = new CodeMsg(500217, "已经登录了");

    public static CodeMsg DEVICEID_NOTSURPPORT = new CodeMsg(500217, "deviceId没有提供");
    public static CodeMsg MOBILE_HAVE_LOGOUT = new CodeMsg(500218, "已经退出了");
    public static CodeMsg MOBILE_VERIFYCODE_NOT_MATCHWITH_DATABASE = new CodeMsg(500219, "验证码错误");


    //商品模块 5003XX


    //订单模块 5004XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

    //秒杀模块 5005XX
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");
    public static CodeMsg MIAOSHA_FAIL = new CodeMsg(500502, "秒杀失败");

    // 钱包模块 5006XX
    public static CodeMsg WALLET_USERINFO_ERROR = new CodeMsg(500600, "钱包创建失败,未查到与用户标识、手机号相关的数据");
    public static CodeMsg WALLET_DEFAULT_NOTALLOWED_DEL = new CodeMsg(500601, "以太币钱包不允许删除");


    // TOKEN 5007XX
    public static CodeMsg TOKEN_FORMAT_ERROR = new CodeMsg(500700, "TOKEN格式不正确");
    public static CodeMsg TOKEN_EXPIRED = new CodeMsg(500701, "TOKEN过期");
    public static CodeMsg TOKEN_ERR_PARAMETER_USERID_LENGHT = new CodeMsg(500701, "生成TOKEN的USERID参数错误");

    // 用户 5008XX
    public static CodeMsg USER_NOT_EXISTS = new CodeMsg(500800, "用户不存在");
    public static CodeMsg USER_INSERT_ERROR = new CodeMsg(500801, "插入用户表(user)失败");
    public static CodeMsg USER_DEVICE_INSERT_ERROR = new CodeMsg(500802, "插入用户设备表(user_device)失败");
    public static CodeMsg USER_ROLE_INSERT_ERROR = new CodeMsg(500803, "插入用户角色表(user_role)失败");
    public static CodeMsg USER_LOGIN_INSERT_ERROR = new CodeMsg(500804, "插入用户登录表(user_login)失败");
    public static CodeMsg USER_GETBY_PHONE_AND_PASSWD_ERROR = new CodeMsg(500804, "按用户手机号和密码查询失败");
    public static CodeMsg USER_ID_SUPPORT_ERROR = new CodeMsg(500804, "用户标识提供错误");


    // 资产 5009XX
    public static CodeMsg ASSET_ADD_ERR = new CodeMsg(500804, "资产添加失败");

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
