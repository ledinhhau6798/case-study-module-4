package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.model.Cart;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.cart.CartDetailItemResDTO;
import com.cg.model.dto.cart.CartItemReqDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartDetail.ICartDetailService;
import com.cg.service.product.IProductService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;


    @GetMapping
    public ResponseEntity<?> getAllCartDetails() {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Optional<Cart> cartOptional = cartService.findByUser(userOptional.get());

        if (cartOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        List<CartDetailItemResDTO> cartDetailItemResDTOS = cartDetailService.getAllCartDetailItemResDTO(cartOptional.get());

        return new ResponseEntity<>(cartDetailItemResDTOS, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItemReqDTO cartItemReqDTO) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Long productId = cartItemReqDTO.getProductId();
        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isEmpty()) {
            throw new DataInputException("Product invalid");
        }

        Product product = productOptional.get();

        Cart cart = cartService.addToCart(cartItemReqDTO, product, userOptional.get());

        List<CartDetailItemResDTO> cartDetailItemResDTOS = cartDetailService.getAllCartDetailItemResDTO(cart);

        return new ResponseEntity<>(cartDetailItemResDTOS, HttpStatus.OK);
    }
}
