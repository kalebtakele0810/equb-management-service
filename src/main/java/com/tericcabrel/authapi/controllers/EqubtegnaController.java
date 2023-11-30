package com.tericcabrel.authapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.services.EqubtegnasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/equbtegnas")
@RestController
@Slf4j
@RequiredArgsConstructor
public class EqubtegnaController {
    private final EqubtegnasService equbtegnasService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equbtegna> register(@RequestBody RegisterDto registerDto) {
        try {
            log.info("Add Equbtegna request:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbtegnasService.addEqubtegna(registerDto);
    }

    @PostMapping("/view")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> view(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbtegnasService.viewEqubtegna(registerEqubtegnaDto);
    }

    @PostMapping("/list")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> list(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        try {
            log.info("List Equbtegna request:" + new ObjectMapper().writeValueAsString(registerEqubtegnaDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbtegnasService.viewListEqubtegnas(registerEqubtegnaDto);
    }
}
