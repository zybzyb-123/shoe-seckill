package com.zhao.shoeSeckill.api.cache.vo;

/**
 * redis键的前缀
 * 设置前缀避免重复
 */
public interface KeyPrefix {

    //key的过期时间
    int expireSeconds();

    //key的前缀
    String getPrefix();
}
