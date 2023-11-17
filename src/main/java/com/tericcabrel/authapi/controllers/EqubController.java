package com.tericcabrel.authapi.controllers;

import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.services.EqubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/equb")
@RestController
@RequiredArgsConstructor
public class EqubController {
    private final EqubService equbService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> register(@RequestBody RegisterDto registerDto) {
        return equbService.addEqub(registerDto);
    }
    @PostMapping("/view")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> view(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbService.viewEqub(registerEqubtegnaDto);
    }

    @PostMapping("/list")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ResponseDto> list(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbService.viewListEqubs(registerEqubtegnaDto);
    }
    @PostMapping("/add-equbtegna")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equb> addEqubtegna(@RequestBody RegisterDto registerDto) {
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
        return equbService.addPayment(registerDto);
    }

}
