package com.hankal.detrust.exception;

import com.hankal.detrust.result.CodeMsg;

/**
 * Created by yegang5211 on 2018/2/11.
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.codeMsg = cm;
    }

    public GlobalException(CodeMsg cm, Exception ex) {
        super(cm.toString());
        this.codeMsg = cm;
        this.codeMsg.setMsg(this.codeMsg.getMsg() + "\\r\\n" + ex.getMessage());
    }

    public CodeMsg getCm() {
        return codeMsg;
    }
}
