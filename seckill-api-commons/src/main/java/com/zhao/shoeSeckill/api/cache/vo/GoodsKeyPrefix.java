package com.zhao.shoeSeckill.api.cache.vo;

import com.zhao.shoeSeckill.pojo.Goods;

import java.io.Serializable;

/**
 * 商品的key前缀
 */
public class GoodsKeyPrefix extends BaseKeyPrefix  implements Serializable {
    public GoodsKeyPrefix(String prefix) {
        super(prefix);
    }

    public GoodsKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    //缓存--商品列表页面
    public static GoodsKeyPrefix goodsKeyPrefix = new GoodsKeyPrefix(60,"goodsList");
    public static GoodsKeyPrefix GOODS_LIST_HTML = new GoodsKeyPrefix(60,"goodsListHtml");

    //缓存--商品详情页
    public static GoodsKeyPrefix goodsDetailKeyPrefix = new GoodsKeyPrefix(60, "goodsDetail");

    // 缓存--商品信息(缓存过期时间为永久)
    public static GoodsKeyPrefix seckillGoodsInf =new GoodsKeyPrefix(0, "goodsInf");

    // 缓存在redis中的商品库存的前缀(缓存过期时间为永久)
    public static GoodsKeyPrefix GOODS_STOCK = new GoodsKeyPrefix(0, "goodsStock");

}
