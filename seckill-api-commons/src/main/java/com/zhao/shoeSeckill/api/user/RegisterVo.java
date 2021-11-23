package com.zhao.shoeSeckill.api.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注册参数
 *
 * @author noodle
 */
@Setter
@Getter
public class RegisterVo implements Serializable {

    @NotNull
    private Long phone;
    @NotNull
    private String nickname;

    private String head;
    @NotNull
    private String password;


    @Override
    public String toString() {
        return "RegisterVo{" +
                "phone=" + phone +
                ", nickname='" + nickname + '\'' +
                ", head='" + head + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
