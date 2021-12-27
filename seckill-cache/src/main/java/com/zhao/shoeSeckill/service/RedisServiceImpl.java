package com.zhao.shoeSeckill.service;

import com.alibaba.fastjson.JSON;
import com.zhao.shoeSeckill.api.cache.RedisServiceApi;
import com.zhao.shoeSeckill.api.cache.vo.KeyPrefix;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service(interfaceClass = RedisServiceApi.class)
public class RedisServiceImpl implements RedisServiceApi {

    @Autowired
    JedisPool jedisPool;


    @Override
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String string = jedis.get(realKey);
            T objValue = stringToBean(string,clazz);
            return objValue;
        }finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String string = beanToString(value);
            if (string==null || string.length()<=0){
                return false;
            }
            int expires = prefix.expireSeconds();
            if (expires<=0){
                jedis.set(realKey,string);
            }else {
                jedis.setex(realKey,expires,string);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            Long del = jedis.del(realKey);
            return del > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    private static <T> T stringToBean(String str,Class<T> clazz){
        if (str==null || str.length()<=0 || clazz==null)
            return null;
        if (clazz==int.class || clazz==Integer.class){
            return (T) Integer.valueOf(str);
        }else if (clazz==long.class || clazz==Long.class){
            return (T) Long.valueOf(str);
        }else if(clazz==String.class ){
            return (T) str;
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    public static <T> String beanToString(T value) {
        if (value == null)
            return null;

        Class<?> clazz = value.getClass();
        /*首先对基本类型处理*/
        if (clazz == int.class || clazz == Integer.class)
            return "" + value;
        else if (clazz == long.class || clazz == Long.class)
            return "" + value;
        else if (clazz == String.class)
            return (String) value;
            /*然后对Object类型的对象处理*/
        else
            return JSON.toJSONString(value);
    }

    private void returnToPool(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
}
