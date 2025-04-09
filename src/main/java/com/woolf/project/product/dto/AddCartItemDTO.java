package com.woolf.project.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartItemDTO {
    private Long productId;
    private int quantity;
}
