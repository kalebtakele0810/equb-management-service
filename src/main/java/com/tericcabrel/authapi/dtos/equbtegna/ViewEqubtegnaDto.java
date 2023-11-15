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
public class ViewEqubtegnaDto implements Serializable {
    private String name;
}
