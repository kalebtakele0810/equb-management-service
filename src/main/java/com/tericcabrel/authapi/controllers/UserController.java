package com.tericcabrel.authapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.identity.User;
import com.tericcabrel.authapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping("/user-details")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/list")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> allUsers(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        try {
            log.info("list users:" + new ObjectMapper().writeValueAsString(registerEqubtegnaDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userService.allUsers(registerEqubtegnaDto);
    }


}
