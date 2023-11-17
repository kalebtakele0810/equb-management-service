package com.tericcabrel.authapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@SuperBuilder
@Data
@RequiredArgsConstructor
public class ResponseDto implements Serializable {
    @JsonProperty("RequestRefID")
    public String requestRefID;
    @JsonProperty("Remark")
    public String remark;
    public Object payload;
}
