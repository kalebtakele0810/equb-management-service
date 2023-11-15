package com.tericcabrel.authapi.services;

import com.tericcabrel.authapi.dtos.users.RegisterUserDto;
import com.tericcabrel.authapi.entities.identity.Role;
import com.tericcabrel.authapi.entities.identity.RoleEnum;
import com.tericcabrel.authapi.entities.identity.User;
import com.tericcabrel.authapi.repositories.RoleRepository;
import com.tericcabrel.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }
}
