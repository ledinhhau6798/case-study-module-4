package com.cg.repository;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.model.dto.cart.CartDetailItemResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findAllByCart(Cart cart);


    @Query("SELECT NEW com.cg.model.dto.cart.CartDetailItemResDTO (" +
                "cd.id, " +
                "cd.product, " +
                "cd.price, " +
                "cd.quantity, " +
                "cd.amount" +
            ") " +
            "FROM CartDetail AS cd " +
            "WHERE cd.cart = :cart"
    )
    List<CartDetailItemResDTO> getAllCartDetailItemResDTO(@Param("cart") Cart cart);
}
