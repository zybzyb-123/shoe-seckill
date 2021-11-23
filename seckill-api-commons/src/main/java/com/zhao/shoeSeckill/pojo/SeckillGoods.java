package com.zhao.shoeSeckill.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillGoods {

    private Long id;
    private Long goodsId;
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
