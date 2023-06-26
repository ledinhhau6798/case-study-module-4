package com.cg.service.cart;


import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.cart.CartItemReqDTO;
import com.cg.repository.CartDetailRepository;
import com.cg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart addToCart(CartItemReqDTO cartItemReqDTO, Product product, User user) {

        Optional<Cart> cartOptional = cartRepository.findByUser(user);
        Cart cart = new Cart();

        if (cartOptional.isEmpty()) {
            cart.setUser(user);
            cart.setTotalAmount(BigDecimal.ZERO);
            cartRepository.save(cart);

            BigDecimal price = product.getPrice();
            Long quantity = cartItemReqDTO.getQuantity();
            BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));


            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setTitle(product.getTitle());
            cartDetail.setPrice(product.getPrice());
            cartDetail.setUnit(product.getUnit());
            cartDetail.setQuantity(cartItemReqDTO.getQuantity());
            cartDetail.setAmount(amount);
            cartDetailRepository.save(cartDetail);

            cart.setTotalAmount(amount);
            cartRepository.save(cart);

        }
        else {
            cart = cartOptional.get();
            Optional<CartDetail> cartDetailOptional = cartDetailRepository.findByCartAndProduct(cart, product);

            if (cartDetailOptional.isEmpty()) {
                BigDecimal price = product.getPrice();
                Long quantity = cartItemReqDTO.getQuantity();
                BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(product);
                cartDetail.setTitle(product.getTitle());
                cartDetail.setPrice(product.getPrice());
                cartDetail.setUnit(product.getUnit());
                cartDetail.setQuantity(cartItemReqDTO.getQuantity());
                cartDetail.setAmount(amount);
                cartDetailRepository.save(cartDetail);

                List<CartDetail> cartDetailList = cartDetailRepository.findAllByCart(cart);
                BigDecimal totalAmount = BigDecimal.ZERO;

                for(CartDetail item : cartDetailList) {
                    BigDecimal itemAmount = item.getAmount();
                    totalAmount = totalAmount.add(itemAmount);
                }

                cart.setTotalAmount(totalAmount);
                cartRepository.save(cart);
            }
            else {
                CartDetail cartDetail = cartDetailOptional.get();
                long currentQuantity = cartDetail.getQuantity();
                long newQuantity = cartItemReqDTO.getQuantity();
                long updateQuantity = currentQuantity + newQuantity;

                BigDecimal price = product.getPrice();
                BigDecimal amount = price.multiply(BigDecimal.valueOf(updateQuantity));

                cartDetail.setPrice(price);
                cartDetail.setQuantity(updateQuantity);
                cartDetail.setAmount(amount);
                cartDetailRepository.save(cartDetail);

                List<CartDetail> cartDetailList = cartDetailRepository.findAllByCart(cart);
                BigDecimal totalAmount = BigDecimal.ZERO;

                for(CartDetail item : cartDetailList) {
                    BigDecimal itemAmount = item.getAmount();
                    totalAmount = totalAmount.add(itemAmount);
                }

                cart.setTotalAmount(totalAmount);
                cartRepository.save(cart);
            }
        }

        return cart;
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
