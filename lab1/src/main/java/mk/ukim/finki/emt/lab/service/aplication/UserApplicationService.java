package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.dto.create.CreateUserDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayUserDto;
import mk.ukim.finki.emt.lab.model.dto.login.LoginResponseDto;
import mk.ukim.finki.emt.lab.model.dto.login.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

//    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
