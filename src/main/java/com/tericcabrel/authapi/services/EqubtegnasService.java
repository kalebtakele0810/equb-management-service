package com.tericcabrel.authapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
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
    private final ObjectMapper objectMapper;

    public ResponseEntity<Equbtegna> addEqubtegna(RegisterDto registerDto) {
        Equbtegna equbtegna = objectMapper.convertValue(registerDto.getPayload(), Equbtegna.class);
        equbtegna.getAccount().setStatus("1");
        return ResponseEntity.ok(equbtegnasRepository.save(equbtegna));

    }
    public ResponseEntity<ResponseDto> viewEqubtegna(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbtegnasRepository.findByMsisdn(viewEqubtegnaDto.getIdentifier()))
                        .build()
        );
    }

    public ResponseEntity<ResponseDto> viewListEqubtegnas(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbtegnasRepository.findAll())
                        .build()
        );
    }
}
