package com.cg.service.cartDetail;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.dto.cart.CartDetailItemResDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartDetailService extends IGeneralService<CartDetail, Long> {

    List<CartDetail> findAllByCart(Cart cart);

    List<CartDetailItemResDTO> getAllCartDetailItemResDTO(Cart cart);
}
