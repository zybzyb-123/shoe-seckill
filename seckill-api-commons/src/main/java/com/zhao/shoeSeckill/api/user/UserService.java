package com.zhao.shoeSeckill.api.user;

import com.zhao.shoeSeckill.api.user.vo.LoginVo;
import com.zhao.shoeSeckill.api.user.vo.RegisterVo;
import com.zhao.shoeSeckill.api.user.vo.UserInfoVo;
import com.zhao.shoeSeckill.api.user.vo.UserVo;
import com.zhao.shoeSeckill.enums.ResponseCode;

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
