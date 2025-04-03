package emt.lab.service.application;

import emt.lab.dto.create.CreateUserDto;
import emt.lab.dto.create.LoginUserDto;
import emt.lab.dto.display.DisplayUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}

