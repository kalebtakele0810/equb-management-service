package com.tericcabrel.authapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tericcabrel.authapi.dtos.RegisterDto;
import com.tericcabrel.authapi.dtos.ResponseDto;
import com.tericcabrel.authapi.dtos.equbtegna.ViewEqubtegnaDto;
import com.tericcabrel.authapi.dtos.payments.ListPaymentsDto;
import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payments")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/list")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ListPaymentsDto> list(@RequestBody ViewEqubtegnaDto addPaymentDto) {
        try {
            log.info("list payment:" + new ObjectMapper().writeValueAsString(addPaymentDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return paymentService.viewListEqubs(addPaymentDto);
    }

    @PostMapping("/list-by-customer")
    @PreAuthorize("hasRole(RoleEnum.USER)")
    public ResponseEntity<ListPaymentsDto> listCustomer(@RequestBody ViewEqubtegnaDto addPaymentDto) {
        try {
            log.info("list payment:" + new ObjectMapper().writeValueAsString(addPaymentDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return paymentService.viewCustomerPayments(addPaymentDto);
    }
}
