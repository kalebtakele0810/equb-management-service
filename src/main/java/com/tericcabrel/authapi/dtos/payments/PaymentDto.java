package com.tericcabrel.authapi.dtos.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tericcabrel.authapi.entities.payment.Payments;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Data
@RequiredArgsConstructor
public class PaymentDto {
    @JsonProperty("EqubId")
    public int equbId;
    @JsonProperty("EqubtegnaId")
    public int equbtegnaId;

    @JsonProperty("EqubType")
    private String equbType;
    @JsonProperty("EqubtegnaName")
    public String equbtegnaName;
    @JsonProperty("EqubtegnaNumber")
    public String equbtegnaNumber;

    public Payments payment;
}
