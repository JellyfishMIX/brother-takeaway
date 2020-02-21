package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.SellerInfoRepository;
import me.jmix.brothertakeaway.entity.SellerInfo;
import me.jmix.brothertakeaway.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo getSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
