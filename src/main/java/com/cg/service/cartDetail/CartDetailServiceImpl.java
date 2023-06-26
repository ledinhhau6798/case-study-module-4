package com.cg.service.cartDetail;


import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.dto.cart.CartDetailItemResDTO;
import com.cg.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartDetailServiceImpl implements ICartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return null;
    }

    @Override
    public List<CartDetail> findAllByCart(Cart cart) {
        return cartDetailRepository.findAllByCart(cart);
    }

    @Override
    public List<CartDetailItemResDTO> getAllCartDetailItemResDTO(Cart cart) {
        return cartDetailRepository.getAllCartDetailItemResDTO(cart);
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return null;
    }

    @Override
    public void delete(CartDetail cartDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
