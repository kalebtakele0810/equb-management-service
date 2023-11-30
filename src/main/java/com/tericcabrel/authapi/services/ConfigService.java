package com.tericcabrel.authapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equb.AddEqub;
import com.tericcabrel.authapi.dtos.equb.AddEqubtegna;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.equb.EqubCategory;
import com.tericcabrel.authapi.entities.equb.EqubType;
import com.tericcabrel.authapi.entities.equb.StartEqub;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.payment.ChannelEnum;
import com.tericcabrel.authapi.entities.payment.PaymentStatusEnum;
import com.tericcabrel.authapi.entities.payment.Payments;
import com.tericcabrel.authapi.repositories.EqubCategoryRepository;
import com.tericcabrel.authapi.repositories.EqubRepository;
import com.tericcabrel.authapi.repositories.EqubTypeRepository;
import com.tericcabrel.authapi.repositories.EqubtegnasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final EqubCategoryRepository equbCategoryRepository;
    private final EqubTypeRepository equbTypeRepository;
    private final ObjectMapper objectMapper;
    public ResponseEntity<EqubType> addEqubType(RegisterDto registerDto) {
        EqubType equbType = objectMapper.convertValue(registerDto.getPayload(), EqubType.class);
        return ResponseEntity.ok(equbTypeRepository.save(equbType));
    }
    public ResponseEntity<EqubCategory> addEqubCategory(RegisterDto registerDto) {
        EqubCategory equbType = objectMapper.convertValue(registerDto.getPayload(), EqubCategory.class);
        return ResponseEntity.ok(equbCategoryRepository.save(equbType));
    }

}
