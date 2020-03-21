package me.jmix.brothertakeaway.service.impl;

import me.jmix.brothertakeaway.dao.ProductInfoRepository;
import me.jmix.brothertakeaway.dto.ShoppingCartDTO;
import me.jmix.brothertakeaway.entity.ProductInfo;
import me.jmix.brothertakeaway.enums.service.ProductServiceStateEnum;
import me.jmix.brothertakeaway.enums.ProductStateEnum;
import me.jmix.brothertakeaway.exception.service.ProductServiceException;
import me.jmix.brothertakeaway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "productInfo")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 通过productId获取对应的productInfo
     * @param productId
     * @return
     */
    @Override
    @Cacheable(key = "#productId", condition = "#productId.length() > 3")
    public ProductInfo getProductInfoByProductId(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        return productInfo;
    }

    /**
     * 获取上架的商品信息列表
     * @return
     */
    @Override
    public List<ProductInfo> getShelvesProductInfo() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStateEnum.SHELVES.getStateCode());
        return productInfoList;
    }

    /**
     * 获取所有商品信息列表
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> getAllProductInfo(Pageable pageable) {
        Page<ProductInfo> productInfoPage = productInfoRepository.findAll(pageable);
        return productInfoPage;
    }

    /**
     * 新增/修改一个产品
     * @param productInfo
     * @return
     */
    @Override
    @CachePut(key = "#productInfo.productId", condition = "#productInfo.productId.length() > 3")
    public ProductInfo saveProduct(ProductInfo productInfo) {
        ProductInfo productInfoResult = productInfoRepository.save(productInfo);
        return productInfoResult;
    }

    /**
     * 增加库存
     * @param shoppingCartDTOList
     */
    @Override
    public void increaseStock(List<ShoppingCartDTO> shoppingCartDTOList) {
        for (ShoppingCartDTO shoppingCartDTO : shoppingCartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(shoppingCartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_NOT_EXIST);
            }

            Integer stockResult = productInfo.getProductStock() + shoppingCartDTO.getProductQuantity();
            if (stockResult < 0) {
                throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stockResult);
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 减少库存
     * @param shoppingCartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<ShoppingCartDTO> shoppingCartDTOList) {
        for (ShoppingCartDTO shoppingCartDTO : shoppingCartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(shoppingCartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_NOT_EXIST);
            }

            Integer stockResult = productInfo.getProductStock() - shoppingCartDTO.getProductQuantity();
            if (stockResult < 0) {
                throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stockResult);
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatus().equals(ProductStateEnum.SHELVES.getStateCode())) {
            throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStateEnum.SHELVES.getStateCode());
        return productInfoRepository.save(productInfo);
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_NOT_EXIST);
        }

        if (productInfo.getProductStatus().equals(ProductStateEnum.DISCONTINUED.getStateCode())) {
            throw new ProductServiceException(ProductServiceStateEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStateEnum.DISCONTINUED.getStateCode());
        return productInfoRepository.save(productInfo);
    }
}
