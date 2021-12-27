package com.zhao.shoeSeckill.api.cache.vo;

import java.io.Serializable;

/**
 * 用户表的key前缀
 */
public class UserKeyPrefix extends BaseKeyPrefix  implements Serializable {

    public UserKeyPrefix(String prefix) {
        super(prefix);
    }

    public static UserKeyPrefix getById = new UserKeyPrefix("id");
    public static UserKeyPrefix getByName = new UserKeyPrefix("name");

}
