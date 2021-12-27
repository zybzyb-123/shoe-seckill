package com.zhao.shoeSeckill.service;

import com.zhao.shoeSeckill.api.cache.DLockApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Service(interfaceClass = DLockApi.class)
public class RedisLockImpl implements DLockApi {

    @Autowired
    private JedisPool jedisPool;

    private final String LOCK_SUCCESS = "OK";
    private final String SET_IF_NOT_EXIST = "NX";
    private final String SET_WITH_EXPIRE_TIME = "PX";



    @Override
    public boolean lock(String lockKey, String uniqueValue, int expireTime) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String result = jedis.set(lockKey,uniqueValue,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,expireTime);
            if (LOCK_SUCCESS.equals(result)){
                return true;
            }
            return false;
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }

    @Override
    public boolean unlock(String lockKey, String uniqueValue) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then "+
                    "return redis.call('del', KEYS[1]) "+
                    "else return 0 "+
                    "end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(uniqueValue));
            if (LOCK_SUCCESS.equals(result)){
                return true;
            }
            return false;
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }
}
