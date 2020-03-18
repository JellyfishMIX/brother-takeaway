package me.jmix.brothertakeaway.service;

public interface SecKillService {
    /**
     * 查询指定秒杀商品的数据信息
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 下单
     * @param productId
     */
    void orderProductMockDiffUser(String productId);
}
