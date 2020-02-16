package me.jmix.brothertakeaway.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {
    // 类目Id
    private String productId;

    // 名字
    private String productName;

    // 单价
    private BigDecimal productPrice;

    // 库存
    private Integer productStock;

    // 商品描述
    private String productDescription;

    // 商品小图
    private String productIcon;

    // 商品状态，0上架，1下架
    // 此处不需要，有单独的上下架操作

    // 类目编号
    private Integer categoryType;
}
