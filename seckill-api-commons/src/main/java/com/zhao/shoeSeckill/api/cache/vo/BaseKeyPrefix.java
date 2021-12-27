package com.zhao.shoeSeckill.api.cache.vo;

public class BaseKeyPrefix implements KeyPrefix{

    int expireSeconds;

    String prefix;

    /**
     * 过期时间默认为0，即不过期
     * @param prefix
     */
    public BaseKeyPrefix(String prefix){
        this(0,prefix);
    }

    public BaseKeyPrefix(int expireSeconds,String prefix){
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }



    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}
