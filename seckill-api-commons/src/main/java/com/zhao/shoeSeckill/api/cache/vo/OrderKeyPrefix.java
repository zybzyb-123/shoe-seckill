package com.zhao.shoeSeckill.api.cache.vo;

import java.io.Serializable;

public class OrderKeyPrefix extends BaseKeyPrefix  implements Serializable {

    public OrderKeyPrefix(String prefix) {
        super(prefix);
    }

    public OrderKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 秒杀订单信息的前缀
     */
    public static OrderKeyPrefix getSeckillOrderByUidGid = new OrderKeyPrefix("getSeckillOrderByUidGid");
    public static OrderKeyPrefix SK_ORDER = new OrderKeyPrefix("SK_ORDER");
}
