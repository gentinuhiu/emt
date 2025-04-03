package emt.lab.web.rest;

import emt.lab.dto.display.DisplayCartDto;
import emt.lab.model.domain.User;
import emt.lab.service.application.CartApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {
    private final CartApplicationService cartApplicationService;

    public CartRestController(CartApplicationService cartApplicationService) {
        this.cartApplicationService = cartApplicationService;
    }

    @GetMapping("/")
    public ResponseEntity<DisplayCartDto> getActiveShoppingCart(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return cartApplicationService.getCartByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/add-to-cart/{id}")
    public ResponseEntity<DisplayCartDto> addToCart(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return cartApplicationService.addToCart(user.getUsername(), id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/rent")
    public ResponseEntity<DisplayCartDto> rent(HttpServletRequest req) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return cartApplicationService.rent(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
