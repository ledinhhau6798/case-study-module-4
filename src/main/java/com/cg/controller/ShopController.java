package com.cg.controller;


import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.User;
import com.cg.model.dto.cart.CartDetailItemResDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartDetail.ICartDetailService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping
    public String showShopPage(Model model) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Optional<Cart> cartOptional = cartService.findByUser(userOptional.get());

        Long totalQuantity = 0L;

        if (cartOptional.isEmpty()) {
            model.addAttribute("totalQuantity", totalQuantity);
        }
        else {
            List<CartDetail> cartDetailList = cartDetailService.findAllByCart(cartOptional.get());

            for (CartDetail item : cartDetailList) {
                totalQuantity += item.getQuantity();
            }
            model.addAttribute("totalQuantity", totalQuantity);
        }

        return "shop/index";
    }
}
