package com.zhao.shoeSeckill.pojo;

import lombok.Data;

@Data
public class SeckillOrder {

    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}