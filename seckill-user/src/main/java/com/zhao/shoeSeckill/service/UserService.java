package com.zhao.shoeSeckill.service;

import com.zhao.shoeSeckill.api.user.LoginVo;
import com.zhao.shoeSeckill.api.user.RegisterVo;
import com.zhao.shoeSeckill.api.user.UserInfoVo;
import com.zhao.shoeSeckill.api.user.UserVo;
import com.zhao.shoeSeckill.result.ResponseCode;

import javax.validation.Valid;

public interface UserService {

    String COOKIE_NAME_TOKEN = "token";

    int login(String username, String password);

    ResponseCode register(RegisterVo userModel);

    boolean checkUsername(String username);

    UserInfoVo getUserInfo(int uuid);

    UserInfoVo updateUserInfo(UserInfoVo userInfoVo);

    String login(@Valid LoginVo loginVo);

    UserVo getUserByPhone(long phone);
}
