package com.zhao.shoeSeckill.service.impl;

import com.zhao.shoeSeckill.api.cache.DLockApi;
import com.zhao.shoeSeckill.api.cache.RedisServiceApi;
import com.zhao.shoeSeckill.api.cache.vo.SkUserKeyPrefix;
import com.zhao.shoeSeckill.api.user.UserService;
import com.zhao.shoeSeckill.api.user.vo.LoginVo;
import com.zhao.shoeSeckill.api.user.vo.RegisterVo;
import com.zhao.shoeSeckill.api.user.vo.UserInfoVo;
import com.zhao.shoeSeckill.api.user.vo.UserVo;
import com.zhao.shoeSeckill.domain.SeckillUser;
import com.zhao.shoeSeckill.enums.ResponseCode;
import com.zhao.shoeSeckill.exception.GlobalException;
import com.zhao.shoeSeckill.persistence.SeckillUserMapper;
import com.zhao.shoeSeckill.util.MD5Util;
import com.zhao.shoeSeckill.util.UUIDUtil;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.Date;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SeckillUserMapper seckillUserMapper;

    @Reference(interfaceClass = RedisServiceApi.class)
    private RedisServiceApi redisService;

    @Reference(interfaceClass = DLockApi.class)
    private DLockApi dLock;


    @Override
    public int login(String username, String password) {
        return 45;
    }

    @Override
    public ResponseCode register(RegisterVo userModel) {
        //加分布式锁
        String uniqueValue = UUIDUtil.uuid() + "-" + Thread.currentThread().getId();
        String lockKey = "redis-lock"+userModel.getPhone();
        boolean lock = dLock.lock(lockKey,uniqueValue,60*1000);
        if (!lock){
            return ResponseCode.WAIT_REGISTER_DONE;
        }
        logger.debug("注册接口加锁成功！");
        //检查用户是否已注册
        SeckillUser seckillUser = seckillUserMapper.getUserByPhone(userModel.getPhone());
        if (seckillUser!=null){
            dLock.unlock(uniqueValue,lockKey);
            return  ResponseCode.USER_EXIST;
        }

        SeckillUser seckillUser1 = new SeckillUser();
        seckillUser1.setHead(userModel.getHead());
        seckillUser1.setPhone(userModel.getPhone());
        seckillUser1.setNickname(userModel.getNickname());
        seckillUser1.setSalt(MD5Util.SALT);
        //加密密码
        String pw = MD5Util.formPassToDbPass(userModel.getPassword(),MD5Util.SALT);
        seckillUser1.setPassword(pw);
        Date date = new Date();
        seckillUser1.setRegisterDate(date);
        //保存数据库
        long count = seckillUserMapper.insertUser(seckillUser1);
        boolean unlock = dLock.unlock(lockKey,uniqueValue);
        if (!unlock){
            return ResponseCode.REGISTER_FAIL;
        }
        logger.debug("注册接口解锁成功！");

        if (count>0){
            return ResponseCode.REGISTER_SUCCESS;
        }
        return ResponseCode.REGISTER_FAIL;
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

        logger.info(loginVo.toString());

        // 获取用户提交的手机号码和密码
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // 判断手机号是否存在(首先从缓存中取，再从数据库取)
        SeckillUser user = this.getSeckillUserByPhone(Long.parseLong(mobile));
        if (user == null)
            throw new GlobalException(ResponseCode.MOBILE_NOT_EXIST);
        logger.info("用户：" + user.toString());

        // 判断手机号对应的密码是否一致
        String dbPassword = user.getPassword();
        String dbSalt = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(password, dbSalt);
        if (!calcPass.equals(dbPassword))
            throw new GlobalException(ResponseCode.PASSWORD_ERROR);

        // 执行到这里表明登录成功，更新用户cookie
        // 生成cookie
        String token = UUIDUtil.uuid();
        // 每次访问都会生成一个新的session存储于redis和反馈给客户端，一个session对应存储一个user对象
        redisService.set(SkUserKeyPrefix.TOKEN, token, user);
        return token;
    }

    /**
     * 从缓存中、数据库中获取用户信息
     * @param phone
     * @return
     */
    private SeckillUser getSeckillUserByPhone(long phone) {
        // 1. 从redis中获取用户数据缓存
        SeckillUser user = redisService.get(SkUserKeyPrefix.SK_USER_PHONE, "_" + phone, SeckillUser.class);

        if (user != null)
            return user;

        // 2. 如果缓存中没有用户数据，则从数据库中查询数据并将数据写入缓存
        // 先从数据库中取出数据
        user = seckillUserMapper.getUserByPhone(phone);
        // 然后将数据返回并将数据缓存在redis中
        if (user != null)
            redisService.set(SkUserKeyPrefix.SK_USER_PHONE, "_" + phone, user);
        return user;
    }

    @Override
    public UserVo getUserByPhone(long phone) {
        UserVo userVo = new UserVo();
        SeckillUser user = seckillUserMapper.getUserByPhone(phone);

        userVo.setUuid(user.getUuid());
        userVo.setSalt(user.getSalt());
        userVo.setRegisterDate(user.getRegisterDate());
        userVo.setPhone(user.getPhone());
        userVo.setPassword(user.getPassword());
        userVo.setNickname(user.getNickname());
        userVo.setLoginCount(user.getLoginCount());
        userVo.setLastLoginDate(user.getLastLoginDate());
        userVo.setHead(user.getHead());

        return userVo;
    }
}
