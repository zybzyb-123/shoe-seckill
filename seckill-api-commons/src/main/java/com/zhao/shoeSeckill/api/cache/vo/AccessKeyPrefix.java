package com.zhao.shoeSeckill.api.cache.vo;

import java.io.Serializable;

/**
 * 访问次数的key前缀
 */
public class AccessKeyPrefix extends BaseKeyPrefix  implements Serializable {
    public AccessKeyPrefix(String prefix) {
        super(prefix);
    }

    public AccessKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKeyPrefix withExpire(int expireSeconds){
        return new AccessKeyPrefix(expireSeconds,"access");
    }
}
