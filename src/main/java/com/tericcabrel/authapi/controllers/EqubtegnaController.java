package com.tericcabrel.authapi.controllers;

import com.tericcabrel.authapi.dtos.equbtegna.RegisterEqubtegnaDto;
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
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<Equbtegna> register(@RequestBody RegisterEqubtegnaDto registerEqubtegnaDto) {
        return equbtegnasService.addEqubtegna(registerEqubtegnaDto);
    }

    @PostMapping("/view")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<Equbtegna> view(@RequestBody ViewEqubtegnaDto registerEqubtegnaDto) {
        return equbtegnasService.viewEqubtegna(registerEqubtegnaDto);
    }
}
