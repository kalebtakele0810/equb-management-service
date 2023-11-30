package com.tericcabrel.authapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.repositories.EqubCategoryRepository;
import com.tericcabrel.authapi.services.EqubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/equb")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EqubController {
    private final EqubService equbService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> register(@RequestBody RegisterDto registerDto) {
        try {
            log.info("Add Equb Request:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.addEqub(registerDto);
    }

    @PostMapping("/view")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> view(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbService.viewEqub(registerEqubtegnaDto);
    }
    @PostMapping("/view-by-join-id")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> viewByJoinedId(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbService.findEqubByJoinedId(registerEqubtegnaDto);
    }
    @PostMapping("/list")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> list(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        try {
            log.info("list equb:" + new ObjectMapper().writeValueAsString(registerEqubtegnaDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.viewListEqubs(registerEqubtegnaDto);
    }

    @PostMapping("/list-equb-types")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> listEqubTypes(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        try {
            log.info("list equb types:" + new ObjectMapper().writeValueAsString(registerEqubtegnaDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.viewListEqubsTypes(registerEqubtegnaDto);
    }
    @PostMapping("/list-equb-category")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> listEqubCategory(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        try {
            log.info("list equb category:" + new ObjectMapper().writeValueAsString(registerEqubtegnaDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.viewListEqubsCategory(registerEqubtegnaDto);
    }

    @PostMapping("/add-equbtegna")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> addEqubtegna(@RequestBody RegisterDto registerDto) {
        try {
            log.info("Add equbtegna to equb:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.addEqubtegnaToEqub(registerDto);
    }

    @PostMapping("/start-equb")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> startEqubs(@RequestBody RegisterDto registerDto) {
        return equbService.startEqub(registerDto);
    }

    @PostMapping("/add-payment")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> addPayment(@RequestBody RegisterDto registerDto) {
        try {
            log.info("add payment:" + new ObjectMapper().writeValueAsString(registerDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return equbService.addPayment(registerDto);
    }

}
