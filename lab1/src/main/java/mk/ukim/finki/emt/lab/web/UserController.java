package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.create.CreateUserDto;
import mk.ukim.finki.emt.lab.model.dto.display.DisplayUserDto;
import mk.ukim.finki.emt.lab.model.dto.login.LoginResponseDto;
import mk.ukim.finki.emt.lab.model.dto.login.LoginUserDto;
import mk.ukim.finki.emt.lab.service.aplication.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {

        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            LoginResponseDto body = userApplicationService.login(loginUserDto).orElseThrow();
            return ResponseEntity.ok(body);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}