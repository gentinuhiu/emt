package emt.lab.service.domain;

import emt.lab.model.domain.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> getCartByUsername(String username);
    Optional<Cart> save(Cart cart);
    Optional<Cart> addToCart(String username, long id);
    Optional<Cart> rent(String username);
}
