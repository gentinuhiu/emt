package emt.lab.dto.display;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Cart;
import emt.lab.model.domain.Copy;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCartDto(List<DisplayBookDto> review, List<DisplayCopyDto> rented) {
    public static DisplayCartDto from(Cart cart) {
        return new DisplayCartDto(DisplayBookDto.from(cart.getReview()), DisplayCopyDto.from(cart.getRented()));
    }
    public static List<DisplayCartDto> from(List<Cart> carts) {
        return carts.stream().map(DisplayCartDto::from).collect(Collectors.toList());
    }
}