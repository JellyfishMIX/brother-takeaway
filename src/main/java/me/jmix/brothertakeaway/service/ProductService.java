package me.jmix.brothertakeaway.service;

import me.jmix.brothertakeaway.dto.ShoppingCartDTO;
import me.jmix.brothertakeaway.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * 通过productId获取对应的productInfo
     * @param productId
     * @return
     */
    ProductInfo getProductInfoByProductId(String productId);

    /**
     * 获取上架的商品信息列表
     * @return
     */
    List<ProductInfo> getShelvesProductInfo();

    /**
     * 获取所有商品信息列表
     * @param pageable
     * @return
     */
    Page<ProductInfo> getAllProductInfo(Pageable pageable);

    /**
     * 新增/修改一个产品
     * @param productInfo
     * @return
     */
    ProductInfo saveProduct(ProductInfo productInfo);

    /**
     * 加库存
     * @param shoppingCartDTOList
     */
    void increaseStock(List<ShoppingCartDTO> shoppingCartDTOList);

    /**
     * 减库存
     * @param shoppingCartDTOList
     */
    void decreaseStock(List<ShoppingCartDTO> shoppingCartDTOList);

    /**
     * 上架
     * @param productId
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     */
    ProductInfo offSale(String productId);
}
