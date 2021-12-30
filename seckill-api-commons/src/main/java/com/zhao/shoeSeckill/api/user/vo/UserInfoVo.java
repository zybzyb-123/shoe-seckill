package com.zhao.shoeSeckill.api.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户信息
 * <p>
 * 注：因为需要通过网络传输此model，所以需要序列化
 *
 * @author noodle
 */
@Setter
@Getter
public class UserInfoVo implements Serializable {

    private Integer uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private int sex;
    private String birthday;
    private String lifeState;
    private String biography;
    private String address;
    private String headAddress;
    private long beginTime; // 创建时间
    private long updateTime;// 更新时间

}
