package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enums.Role;
import mk.ukim.finki.emt.lab.model.exceptions.*;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.security.JwtHelper;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {   this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException("Username or password cannot be empty");
        if (!password.equals(repeatPassword)) throw new RuntimeException("Passwords do not match");
        if (userRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Username is already in use");
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Username and password cannot be empty");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
                
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    @Override
    public User getAuthenticatedUser(String token) {
        String username = jwtHelper.extractUsername(token);
        return findByUsername(username);
    }

}

