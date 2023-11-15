package com.tericcabrel.authapi.services;

import com.tericcabrel.authapi.dtos.equbtegna.RegisterEqubtegnaDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.repositories.EqubtegnasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EqubtegnasService {
    private final EqubtegnasRepository equbtegnasRepository;

    public ResponseEntity<Equbtegna> addEqubtegna(RegisterEqubtegnaDto registerEqubtegnaDto) {
        return ResponseEntity.ok(equbtegnasRepository.save(Equbtegna.builder()
                .fullName(registerEqubtegnaDto.getFullName())
                .msisdn(registerEqubtegnaDto.getMsisdn())
                .age(registerEqubtegnaDto.getAge())
                .gender(registerEqubtegnaDto.getGender())
                .build()));

    }

    public ResponseEntity<Equbtegna> viewEqubtegna(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(equbtegnasRepository.findByMsisdn(viewEqubtegnaDto.getName()));
    }
}
