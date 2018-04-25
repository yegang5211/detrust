package com.hankal.detrust.result;

/**
 * Created by yegang5211 on 2018/2/9.
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null)
            return;

        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /*
    * 成功的时候调用
     */
    public static <T> Result<T> success(T data) {
        return new Result(data);
    }

    /*
    * 失败的时候调用
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
