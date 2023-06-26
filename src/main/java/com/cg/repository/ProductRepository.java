package com.cg.repository;

import com.cg.model.Product;

import java.util.List;

import com.cg.model.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.cg.model.dto.product.ProductDTO ( " +
                "pr.id, " +
                "pr.title, " +
                "pr.price, " +
                "pr.quantity, " +
                "pr.description, " +
                "pr.unit, " +
                "pr.category, " +
                "pr.productAvatar " +
            ") " +
            "FROM Product AS pr "
    )
    List<ProductDTO> findAllProductDTO();


}
