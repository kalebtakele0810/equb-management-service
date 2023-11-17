package com.tericcabrel.authapi.controllers;

import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.services.EqubtegnasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/equbtegnas")
@RestController
@RequiredArgsConstructor
public class EqubtegnaController {
    private final EqubtegnasService equbtegnasService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equbtegna> register(@RequestBody RegisterDto registerDto) {
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
        return equbtegnasService.viewListEqubtegnas(registerEqubtegnaDto);
    }
}
