package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.ProductInfoRepository;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.enums.ProductStateEnum;
import me.jmix.brothertakeaway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo getProductInfoByProductId(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        return productInfo;
    }

    @Override
    public List<ProductInfo> getShelvesProductInfo() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStateEnum.SHELVES.getStateCode());
        return productInfoList;
    }

    @Override
    public Page<ProductInfo> getAllProductInfo(Pageable pageable) {
        Page<ProductInfo> productInfoPage = productInfoRepository.findAll(pageable);
        return productInfoPage;
    }

    @Override
    public ProductInfo addProduct(ProductInfo productInfo) {
        ProductInfo productInfoResult = productInfoRepository.save(productInfo);
        return productInfoResult;
    }
}
