package com.zhao.shoeSeckill.api.cache;

/**
 * 分布式锁接口
 */
public interface DLockApi {

    boolean lock(String lockKey,String uniqueValue,int expireTime);

    boolean unlock(String lockKey,String uniqueValue);
}
