package emt.lab.service.application.impl;

import emt.lab.dto.display.DisplayCartDto;
import emt.lab.service.application.CartApplicationService;
import emt.lab.service.domain.CartService;
import emt.lab.service.domain.impl.CartServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartApplicationServiceImpl implements CartApplicationService {
    private final CartService cartService;

    public CartApplicationServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public Optional<DisplayCartDto> getCartByUsername(String username) {
        return cartService.getCartByUsername(username).map(DisplayCartDto::from);
    }

    @Override
    public Optional<DisplayCartDto> addToCart(String username, long id) {
        return cartService.addToCart(username, id).map(DisplayCartDto::from);
    }

    @Override
    public Optional<DisplayCartDto> rent(String username) {
        return cartService.rent(username).map(DisplayCartDto::from);
    }
}
