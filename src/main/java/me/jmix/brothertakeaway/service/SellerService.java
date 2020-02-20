package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.entity.SellerInfo;

public interface SellerService {
    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo getSellerInfoByOpenid(String openid);
}
