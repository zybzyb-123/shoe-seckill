package com.zhao.shoeSeckill.api.user.vo;

import com.zhao.shoeSeckill.validator.IsMobile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用于接收客户端请求中的表单数据
 * 使用JSR303完成参数校验
 *
 * @author noodle
 */
@Setter
@Getter
public class LoginVo implements Serializable{

    /**
     * 通过注解的方式校验手机号（JSR303）
     */
    @NotNull
    @IsMobile// 自定义的注解完成手机号的校验
    private String mobile;

    /**
     * 通过注解的方式校验密码（JSR303）
     */
    @NotNull
    @Length(min = 32)// 长度最小为32
    private String password;


    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
