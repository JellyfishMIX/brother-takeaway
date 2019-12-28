package me.jmix.brothertakeaway.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_product_category")
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class ProductCategory {
    // 类目Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    // 类目名称
    private String categoryName;

    // 类目分类
    private Integer categoryType;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    public ProductCategory() {

    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
