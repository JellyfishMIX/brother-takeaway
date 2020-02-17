package me.jmix.brothertakeaway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.jmix.brothertakeaway.enums.ProductStateEnum;
import me.jmix.brothertakeaway.utils.EnumUtil;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_product_info")
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
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
    private Integer productStatus = ProductStateEnum.SHELVES.getStateCode();

    // 类目编号
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStateEnum getProductStateEnum() {
        return EnumUtil.getByCode(productStatus, ProductStateEnum.class);
    }
}
