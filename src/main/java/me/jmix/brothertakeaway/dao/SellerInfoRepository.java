package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo  findByOpenid(String openid);
}
