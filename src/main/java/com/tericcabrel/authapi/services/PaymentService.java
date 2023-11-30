package com.tericcabrel.authapi.services;

import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.dtos.payments.ListPaymentsDto;
import com.tericcabrel.authapi.dtos.payments.PaymentDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.payment.Payments;
import com.tericcabrel.authapi.repositories.EqubRepository;
import com.tericcabrel.authapi.repositories.EqubtegnasRepository;
import com.tericcabrel.authapi.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final EqubtegnasRepository equbtegnasRepository;
    private final EqubRepository equbRepository;


    public ResponseEntity<ListPaymentsDto> viewListEqubs(ViewEqubtegnaDto viewEqubtegnaDto) {
        List<Payments> payments = (List<Payments>) paymentRepository.findAll();
        List<PaymentDto> listPaymentsDto = new ArrayList<>();
        for (Payments payment : payments) {
            Equbtegna equbtegna = equbtegnasRepository.findByPayments_Id(payment.getId());
            Equb equb = equbRepository.findByPayments_Id(payment.getId());
            listPaymentsDto.add(PaymentDto.builder()
                    .equbtegnaId(equbtegna.getId())
                    .equbtegnaName(equbtegna.getFirstName() + " " + equbtegna.getLastName())
                    .equbtegnaNumber(equbtegna.getMsisdn())
                    .equbId(equb.getId())
                    .equbType(equb.getName())
                    .payment(payment)
                    .build());
        }
        return ResponseEntity.ok(
                ListPaymentsDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payments(listPaymentsDto)
                        .build()
        );
    }

    public ResponseEntity<ListPaymentsDto> viewCustomerPayments(ViewEqubtegnaDto viewEqubtegnaDto) {
        List<Payments> payments = equbtegnasRepository.findByMsisdn(viewEqubtegnaDto.getIdentifier()).getPayments();
//        List<Payments> payments = (List<Payments>) paymentRepository.findAll();
        List<PaymentDto> listPaymentsDto = new ArrayList<>();
        try {
            for (Payments payment : payments) {
                Equbtegna equbtegna = equbtegnasRepository.findByPayments_Id(payment.getId());
                Equb equb = equbRepository.findByPayments_Id(payment.getId());
                listPaymentsDto.add(PaymentDto.builder()
                        .equbtegnaId(equbtegna.getId())
                        .equbtegnaName(equbtegna.getFirstName() + " " + equbtegna.getLastName())
                        .equbtegnaNumber(equbtegna.getMsisdn())
                        .equbId(equb.getId())
                        .equbType(equb.getName())
                        .payment(payment)
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(
                ListPaymentsDto.builder()
                        .requestRefID(viewEqubtegnaDto.getRequestRefID())
                        .remark(viewEqubtegnaDto.getRemark())
                        .payments(listPaymentsDto)
                        .build()
        );
    }
}
