package com.tericcabrel.authapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equb.AddEqubtegna;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.equb.StartEqub;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.payment.ChannelEnum;
import com.tericcabrel.authapi.entities.payment.PaymentStatusEnum;
import com.tericcabrel.authapi.entities.payment.Payments;
import com.tericcabrel.authapi.repositories.EqubRepository;
import com.tericcabrel.authapi.repositories.EqubtegnasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EqubService {
    private final EqubRepository equbRepository;
    private final EqubtegnasRepository equbtegnasRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Equb> addEqub(RegisterDto registerDto) {
        Equb equb = objectMapper.convertValue(registerDto.getPayload(), Equb.class);
        return ResponseEntity.ok(equbRepository.save(equb));
    }

    public ResponseEntity<Equb> addEqubtegnaToEqub(RegisterDto registerDto) {
        AddEqubtegna addEqubtegna = objectMapper.convertValue(registerDto.getPayload(), AddEqubtegna.class);
        Optional<Equb> equbOptional = equbRepository.findById(Integer.parseInt(addEqubtegna.getEqubId()));
        Equbtegna equbtegna = equbtegnasRepository.findByMsisdn(addEqubtegna.getIdentifier());
        if (equbOptional.isPresent() && Objects.nonNull(equbtegna)) {
            Equb equb = equbOptional.get();
            List<Equbtegna> equbtegnas = equb.getEqubtegnas();
            equbtegnas.add(equbtegna);
            equb.setEqubtegnas(equbtegnas);
            return ResponseEntity.ok(equbRepository.save(equb));
        } else {
            return ResponseEntity.badRequest().body(new Equb());
        }

    }

    public ResponseEntity<Equb> startEqub(RegisterDto registerDto) {
        AddEqubtegna addEqubtegna = objectMapper.convertValue(registerDto.getPayload(), AddEqubtegna.class);
        Optional<Equb> equbOptional = equbRepository.findById(Integer.parseInt(addEqubtegna.getEqubId()));
        Equbtegna equbtegna = equbtegnasRepository.findByMsisdn(addEqubtegna.getIdentifier());
        if (equbOptional.isPresent() && Objects.nonNull(equbtegna)) {
            Equb equb = equbOptional.get();
            List<StartEqub> equbStartEqubs = equb.getStartEqubs();
            List<StartEqub> equbStartEqubs2 = equbtegna.getStartEqubs();
            StartEqub startEqub = StartEqub.builder()
                    .status(addEqubtegna.getStatus())
                    .joinedDate(new Date())
                    .subGroup(addEqubtegna.getSubGroup())
                    .numberOfTimes(addEqubtegna.getNumberOfTime())
                    .build();
            equbStartEqubs.add(startEqub);
            equbStartEqubs2.add(startEqub);
            equb.setStartEqubs(equbStartEqubs);
            equbtegna.setStartEqubs(equbStartEqubs2);
            equbtegnasRepository.save(equbtegna);
            return ResponseEntity.ok(equbRepository.save(equb));
        } else {
            return ResponseEntity.badRequest().body(new Equb());
        }

    }


    public ResponseEntity<Equb> addPayment(RegisterDto registerDto) {
        AddEqubtegna addEqubtegna = objectMapper.convertValue(registerDto.getPayload(), AddEqubtegna.class);
        Optional<Equb> equbOptional = equbRepository.findById(Integer.parseInt(addEqubtegna.getEqubId()));
        Equbtegna equbtegna = equbtegnasRepository.findByMsisdn(addEqubtegna.getIdentifier());
        if (equbOptional.isPresent() && Objects.nonNull(equbtegna)) {
            Equb equb = equbOptional.get();
            List<Payments> equbPayments = equb.getPayments();
            List<Payments> equbtegnaPayments = equbtegna.getPayments();
            Payments paymentInfo = Payments.builder()
                    .channel(ChannelEnum.valueOf(addEqubtegna.getChannel()))
                    .amount(addEqubtegna.getAmount())
                    .transactionId(addEqubtegna.getTransactionId())
                    .paymentDate(new Date())
                    .status(PaymentStatusEnum.valueOf(addEqubtegna.getStatus()))
                    .build();
            equbPayments.add(paymentInfo);
            equbtegnaPayments.add(paymentInfo);
            equb.setPayments(equbPayments);
            equbtegna.setPayments(equbtegnaPayments);
            equbtegnasRepository.save(equbtegna);
            return ResponseEntity.ok(equbRepository.save(equb));
        } else {
            return ResponseEntity.badRequest().body(new Equb());
        }

    }

    public ResponseEntity<ResponseDto> viewEqub(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbRepository.findByEqubtegnas_Msisdn(viewEqubtegnaDto.getIdentifier()))
                        .build()
        );
    }

    public ResponseEntity<ResponseDto> viewListEqubs(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbRepository.findAll())
                        .build()
        );
    }
}
