package emt.lab.service.domain.impl;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Cart;
import emt.lab.model.domain.Copy;
import emt.lab.model.domain.User;
import emt.lab.model.exception.NoAvailableCopiesException;
import emt.lab.repository.CartRepository;
import emt.lab.service.domain.CartService;
import emt.lab.service.domain.CopyService;
import emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CopyService copyService;
    private final CartRepository cartRepository;

    public CartServiceImpl(UserService userService, CopyService copyService, CartRepository cartRepository) {
        this.userService = userService;
        this.copyService = copyService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getCartByUsername(String username) {
        User user = userService.findByUsername(username);
        if(user.getCart() == null){
            Cart cart = new Cart();
            cart = cartRepository.save(cart);
            user.setCart(cart);
        }
        return Optional.of(user.getCart());
    }

    @Override
    public Optional<Cart> save(Cart cart) {
        return Optional.of(cartRepository.save(cart));
    }

    @Override
    public Optional<Cart> addToCart(String username, long id) {
        User user = userService.findByUsername(username);

        if(user.getCart() == null){
            Cart cart = new Cart();
            cart = cartRepository.save(cart);
            user.setCart(cart);
        }

        return Optional.of(user.getCart()).map(cart -> {
            List<Copy> copies = copyService.findAll().stream().filter(c -> c.getBook().getId().equals(id) && !c.isRented()).toList();

            if(copies.isEmpty()){
                throw new NoAvailableCopiesException(id);
            }

            cart.getReview().add(copies.getFirst().getBook());
            userService.updateByUsername(username, user);
            return cartRepository.save(cart);
        });
    }

    @Override
    public Optional<Cart> rent(String username) {
        User user = userService.findByUsername(username);
        if(user.getCart() == null){
            Cart cart = new Cart();
            cart = cartRepository.save(cart);
            user.setCart(cart);
        }

        return Optional.of(user.getCart()).map(cart -> {
            List<Book> reviews = cart.getReview();
            List<Copy> allCopies = copyService.findAll().stream().filter(c -> !c.isRented()).toList();

            for(int i = 0; i < reviews.size(); i++){
                Book review = reviews.get(i);
                List<Copy> copies = allCopies.stream().filter(c -> c.getBook().getId().equals(review.getId())).toList();

                if(!copies.isEmpty()){
                    Copy copy = copies.getFirst();
                    copy.setRented(true);
                    cart.getRented().add(copy);
                    copyService.update(copy.getId(), copy);
                    allCopies = allCopies.stream().filter(c -> !c.isRented()).toList();
                }
            }

            cart.setReview(new ArrayList<>());
            return cartRepository.save(cart);
        });
    }
}
