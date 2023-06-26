package com.cg.model.dto.cart;

import com.cg.model.Product;
import com.cg.model.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailItemResDTO {

    private Long id;
    private ProductDTO product;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;

    public CartDetailItemResDTO(Long id, Product product, BigDecimal price, Long quantity, BigDecimal amount) {
        this.id = id;
        this.product = product.toProductDTO();
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }
}
