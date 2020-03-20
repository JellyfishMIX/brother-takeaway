package me.jmix.brothertakeaway.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JellyfishMIX
 */
@Data
public class ProductCategoryVO implements Serializable {
    private static final long serialVersionUID = -2551891241937347726L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
