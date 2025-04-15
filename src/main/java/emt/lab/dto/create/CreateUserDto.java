package emt.lab.dto.create;

import emt.lab.model.domain.Cart;
import emt.lab.model.domain.User;
import emt.lab.model.enumeration.ROLE;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        ROLE role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

