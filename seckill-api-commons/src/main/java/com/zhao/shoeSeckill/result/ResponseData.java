package com.zhao.shoeSeckill.result;

import java.io.Serializable;

/**
 * 用户接口返回结果
 *
 * @param <T> 数据实体类型
 * @author noodle
 */
public class ResponseData<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态短语
     */
    private String msg;

    /**
     * 数据实体
     */
    private T data;

    /**
     * 定义为private是为了在防止在controller中直接new
     *
     * @param data
     */
    private ResponseData(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private ResponseData(ResponseCode responseCode) {
        if (responseCode == null)
            return;
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    /**
     * 只有get没有set，是为了防止在controller使用set对结果修改，从而达到一个更好的封装效果
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 业务处理成功返回结果，直接返回业务数据
     *
     * @param data
     * @return
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(data);
    }

    /**
     * 业务处理信息
     *
     * @param serverError
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> info(ResponseCode serverError) {
        return new ResponseData<T>(serverError);
    }

    /**
     * 业务处理成功
     *
     * @param serverError
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> success(ResponseCode serverError) {
        return new ResponseData<T>(serverError);
    }

    /**
     * 业务处理失败
     *
     * @param serverError
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> error(ResponseCode serverError) {
        return new ResponseData<T>(serverError);
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
