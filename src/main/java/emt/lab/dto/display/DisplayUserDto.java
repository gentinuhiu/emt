package emt.lab.dto.display;

import emt.lab.model.domain.Cart;
import emt.lab.model.domain.User;
import emt.lab.model.enumeration.ROLE;

public record DisplayUserDto(String username, String name, String surname, ROLE role, Cart cart) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getCart()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}
