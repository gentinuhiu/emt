package emt.lab.service.application;

import emt.lab.dto.display.DisplayCartDto;

import java.util.Optional;

public interface CartApplicationService {
    Optional<DisplayCartDto> getCartByUsername(String username);
    Optional<DisplayCartDto> addToCart(String username, long id);
    Optional<DisplayCartDto> rent(String username);
}
