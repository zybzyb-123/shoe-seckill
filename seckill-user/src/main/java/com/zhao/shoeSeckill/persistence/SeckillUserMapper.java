package com.zhao.shoeSeckill.persistence;

import com.zhao.shoeSeckill.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SeckillUserMapper {

    /**
     * 通过 phone 查询用户信息
     */
    SeckillUser getUserByPhone(@Param("phone") Long phone);

    /**
     * 更新用户信息
     */
    @Update("UPDATE seckill_user SET password=#{password} WHERE id=#{id}")
    void updatePassword(SeckillUser updatedUser);


    /**
     * 插入一条用户信息到数据库中
     */
    long insertUser(SeckillUser seckillUser);

    /**
     * 查询电话号码
     */
    long findPhone(long phone);
}
