package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.enums.SecKillStateEnum;
import me.jmix.brothertakeaway.exception.SecKillException;
import me.jmix.brothertakeaway.service.RedisLock;
import me.jmix.brothertakeaway.service.SecKillService;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {
    private static final int TIMEOUT = 10 * 1000;   // 超时时间 10s

    @Autowired
    private RedisLock redisLock;

    // 使用Map模拟产品表

    // 国庆活动，皮蛋粥特价，限量10000份
    private static Map<String, Integer> products;
    private static Map<String, Integer> stock;
    private static Map<String, String> orders;
    static {
        // 模拟多张表，商品信息表，库存表，秒杀成功订单表
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);  // 表示总库存
        stock.put("123456", 10000); // 表示剩余库存
    }

    /**
     * 查询指定秒杀商品的数据信息
     * @param productId
     * @return
     */
    @Override
    public String querySecKillProductInfo(String productId) {
        return null;
    }

    /**
     * 展示当前活动数据
     * @return
     */
    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量份。"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + "份"
                + " 该商品成功下单用户数目："
                + orders.size() + " 人";
    }

    /**
     * 下单
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {
        // 加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SecKillException(SecKillStateEnum.THREAD_EXCESS);
        }

        // 1. 查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum <= 0) {
            throw new SecKillException(SecKillStateEnum.NO_STOCK);
        } else {
            // 2. 下单(模拟不同用户openid不同)
            orders.put(KeyUtil.getUniqueKey(), productId);
            // 3. 减库存
            stockNum--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        // 解锁
        redisLock.unlock(productId, String.valueOf(time));
    }
}
