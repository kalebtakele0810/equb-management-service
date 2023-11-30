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
public class EqubService {
    private final EqubRepository equbRepository;
    private final EqubtegnasRepository equbtegnasRepository;
    private final ObjectMapper objectMapper;
    private final EqubCategoryRepository equbCategoryRepository;
    private final EqubTypeRepository equbTypeRepository;

    public ResponseEntity<Equb> addEqub(RegisterDto registerDto) {

        AddEqub addEqub = objectMapper.convertValue(registerDto.getPayload(), AddEqub.class);
        EqubType equbType = equbTypeRepository.findById(Integer.valueOf(addEqub.getEqubType())).get();
        EqubCategory equbCategory = equbCategoryRepository.findById(Integer.valueOf(addEqub.getEqubCategory())).get();

        Calendar cal = Calendar.getInstance();
        cal.setTime(addEqub.getStarted_date());
        cal.add(Calendar.DAY_OF_WEEK, addEqub.getRound() * equbType.getNumberOfDays());
        Date endDate = cal.getTime();
        Equb equb = Equb.builder()
                .name(addEqub.getName())
                .amount(addEqub.getAmount())
                .round(addEqub.getRound())
                .started_date(addEqub.getStarted_date())
                .end_date(endDate)
                .status("0")
                .equbAgreement(addEqub.getEqubAgreement())
                .equbType(equbType)
                .equbCategory(equbCategory)
                .build();
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

  public ResponseEntity<ResponseDto> findEqubByJoinedId(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbRepository.findByStartEqubs_Id(Integer.valueOf(viewEqubtegnaDto.getIdentifier())))
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

    public ResponseEntity<ResponseDto> viewListEqubsCategory(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbCategoryRepository.findAll())
                        .build()
        );
    }

    public ResponseEntity<ResponseDto> viewListEqubsTypes(ViewEqubtegnaDto viewEqubtegnaDto) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payload(equbTypeRepository.findAll())
                        .build()
        );
    }
}
