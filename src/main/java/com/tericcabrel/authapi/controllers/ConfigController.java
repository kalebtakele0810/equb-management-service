package com.tericcabrel.authapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.equb.EqubCategory;
import com.tericcabrel.authapi.entities.equb.EqubType;
import com.tericcabrel.authapi.services.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/config")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ConfigController {
    private final ConfigService configService;


    @PostMapping("/add-equb-type")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EqubType> addEqubType(@RequestBody RegisterDto registerDto) {
        try {
            log.info("Add Equb Type:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return configService.addEqubType(registerDto);
    }

    @PostMapping("/add-equb-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EqubCategory> addEqubCategory(@RequestBody RegisterDto registerDto) {
        try {
            log.info("Add Equb Category:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return configService.addEqubCategory(registerDto);
    }
}
