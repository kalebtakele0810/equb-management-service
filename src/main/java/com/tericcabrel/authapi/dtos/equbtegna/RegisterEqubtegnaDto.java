package com.tericcabrel.authapi.dtos.equbtegna;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Setter
@Getter
@SuperBuilder
@Data
@RequiredArgsConstructor
public class RegisterEqubtegnaDto implements Serializable {
    private String fullName;
    private String msisdn;
    private int age;
    private String gender;
}
