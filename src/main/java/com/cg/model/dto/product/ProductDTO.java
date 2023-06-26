package com.cg.model.dto.product;

import com.cg.model.Category;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarDTO avatar;


    public ProductDTO(Long id, String title, BigDecimal price, Long quantity,
                      String description, String unit, Category category, ProductAvatar productAvatar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.unit = unit;
        this.categoryTitle = category.getTitle();
        this.avatar = productAvatar.toProductAvatarDTO();
    }
}
