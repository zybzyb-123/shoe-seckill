package com.zhao.shoeSeckill.enums;

import java.io.Serializable;

public class ResponseCode implements Serializable {

    private int code;
    private String msg;

    /**
     * 通用异常
     */
    public static ResponseCode SUCCESS = new ResponseCode(0, "success");
    public static ResponseCode SERVER_ERROR = new ResponseCode(500100, "服务端异常");
    public static ResponseCode BIND_ERROR = new ResponseCode(500101, "参数校验异常：%s");
    public static ResponseCode REQUEST_ILLEGAL = new ResponseCode(500102, "请求非法");
    public static ResponseCode VERITF_FAIL = new ResponseCode(500103, "校验失败，请重新输入表达式结果或刷新校验码重新输入");
    public static ResponseCode ACCESS_LIMIT_REACHED = new ResponseCode(500104, "访问太频繁！");

    /**
     * 用户模块 5002XX
     */
    public static ResponseCode SESSION_ERROR = new ResponseCode(500210, "Session不存在或者已经失效，请返回登录！");
    public static ResponseCode PASSWORD_EMPTY = new ResponseCode(500211, "登录密码不能为空");
    public static ResponseCode MOBILE_EMPTY = new ResponseCode(500212, "手机号不能为空");
    public static ResponseCode MOBILE_ERROR = new ResponseCode(500213, "手机号格式错误");
    public static ResponseCode MOBILE_NOT_EXIST = new ResponseCode(500214, "手机号不存在");
    public static ResponseCode PASSWORD_ERROR = new ResponseCode(500215, "密码错误");
    public static ResponseCode USER_EXIST = new ResponseCode(500216, "用户已经存在，无需重复注册");
    public static ResponseCode REGISTER_SUCCESS = new ResponseCode(500217, "注册成功");
    public static ResponseCode REGISTER_FAIL = new ResponseCode(500218, "注册异常");
    public static ResponseCode FILL_REGISTER_INFO = new ResponseCode(500219, "请填写注册信息");
    public static ResponseCode WAIT_REGISTER_DONE = new ResponseCode(500220, "等待注册完成");
    public static ResponseCode SERVER_BUSY = new ResponseCode(500105, "服务器繁忙，请稍后！");

    //登录模块 5002XX

    //商品模块 5003XX

    //订单模块 5004XX
    public static ResponseCode ORDER_NOT_EXIST = new ResponseCode(500400, "订单不存在");

    /**
     * 秒杀模块 5005XX
     */
    public static ResponseCode SECKILL_OVER = new ResponseCode(500500, "商品已经秒杀完毕");
    public static ResponseCode REPEATE_SECKILL = new ResponseCode(500501, "不能重复秒杀");
    public static ResponseCode SECKILL_FAIL = new ResponseCode(500502, "秒杀失败");
    public static ResponseCode SECKILL_PARM_ILLEGAL = new ResponseCode(500503, "秒杀请求参数异常：%s");
    public static ResponseCode SECKILL_TIME_ILLEGAL = new ResponseCode(500504, "非法请求，秒杀还没开始");
    public static ResponseCode SECKILL_DOODS_ILLEGAL = new ResponseCode(500505, "非法请求，商品ID出错");

    /**
     * 构造器定义为private是为了防止controller直接new
     *
     * @param code
     * @param msg
     */
    public ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 动态地填充msg字段
     *
     * @param args
     * @return
     */
    public ResponseCode fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);// 将arg格式化到msg中，组合成一个message
        return new ResponseCode(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
