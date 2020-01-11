package me.jmix.brothertakeaway.dto;

import lombok.Data;

@Data
public class ShoppingCartDTO {
    private String productId;
    private Integer productQuantity;

    public ShoppingCartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
