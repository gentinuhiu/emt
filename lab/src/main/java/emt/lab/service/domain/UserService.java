package emt.lab.service.domain;

import emt.lab.model.domain.User;
import emt.lab.model.enumeration.ROLE;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, ROLE role);

    User login(String username, String password);

    User findByUsername(String username);

    Optional<User> updateByUsername(String username, User user);
}
