package com.zhao.shoeSeckill.api.cache;

import com.zhao.shoeSeckill.api.cache.vo.KeyPrefix;

public interface RedisServiceApi {

    <T> T get(KeyPrefix prefix,String key,Class<T> clazz);

    <T> boolean set(KeyPrefix prefix,String key,T value);

    boolean exists(KeyPrefix prefix, String key);

    long incr(KeyPrefix prefix,String key);

    long decr(KeyPrefix prefix,String key);

    boolean delete(KeyPrefix prefix,String key);

}
