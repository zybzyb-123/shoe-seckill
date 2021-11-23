package com.zhao.shoeSeckill.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillUser {

    private Long uuid;
    private Long phone;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

    @Override
    public String toString() {
        return "SeckillUser{" +
                "uuid=" + uuid +
                ", phone=" + phone +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", registerDate=" + registerDate +
                ", lastLoginDate=" + lastLoginDate +
                ", loginCount=" + loginCount +
                '}';
    }
}
