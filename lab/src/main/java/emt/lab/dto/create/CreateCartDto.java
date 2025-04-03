package emt.lab.dto.create;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Cart;
import emt.lab.model.domain.Copy;

import java.util.List;

public record CreateCartDto(List<Book> review, List<Copy> rented) {
    public Cart toCart(){
        return new Cart(review, rented);
    }
}
