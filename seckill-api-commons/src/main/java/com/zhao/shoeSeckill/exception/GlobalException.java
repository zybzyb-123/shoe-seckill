package com.zhao.shoeSeckill.exception;


import com.zhao.shoeSeckill.enums.ResponseCode;

/**
 * 全局异常处理器
 *
 * @author noodle
 */
public class GlobalException extends RuntimeException {

    private ResponseCode codeMsg;

    /**
     * 使用构造器接收CodeMsg
     *
     * @param codeMsg
     */
    public GlobalException(ResponseCode codeMsg) {
        this.codeMsg = codeMsg;
    }

    public ResponseCode getCodeMsg() {
        return codeMsg;
    }
}
