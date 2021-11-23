package com.zhao.shoeSeckill.service.impl;

import com.zhao.shoeSeckill.api.user.LoginVo;
import com.zhao.shoeSeckill.api.user.RegisterVo;
import com.zhao.shoeSeckill.api.user.UserInfoVo;
import com.zhao.shoeSeckill.api.user.UserVo;
import com.zhao.shoeSeckill.result.ResponseCode;
import com.zhao.shoeSeckill.service.UserService;

import javax.validation.Valid;

public class UserServiceImpl implements UserService {
    @Override
    public int login(String username, String password) {
        return 0;
    }

    @Override
    public ResponseCode register(RegisterVo userModel) {
        return null;
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }

    @Override
    public UserInfoVo getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoVo updateUserInfo(UserInfoVo userInfoVo) {
        return null;
    }

    @Override
    public String login(@Valid LoginVo loginVo) {
        return null;
    }

    @Override
    public UserVo getUserByPhone(long phone) {
        return null;
    }
}
