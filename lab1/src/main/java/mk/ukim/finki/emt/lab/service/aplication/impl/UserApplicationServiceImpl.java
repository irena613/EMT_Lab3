package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.dto.login.LoginResponseDto;
import mk.ukim.finki.emt.lab.security.JwtHelper;
import mk.ukim.finki.emt.lab.service.aplication.UserApplicationService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.emt.lab.model.dto.create.CreateUserDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayUserDto;
import mk.ukim.finki.emt.lab.model.dto.login.LoginUserDto;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }


    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    //    @Override
//    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
//        return Optional.of(DisplayUserDto.from(userService.login(
//                loginUserDto.username(),
//                loginUserDto.password()
//        )));
//    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password());

        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginResponseDto(token));
    }


    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}
