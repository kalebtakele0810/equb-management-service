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
public class RegisterDto implements Serializable {
    @JsonProperty("RequestRefID")
    public String requestRefID;
    @JsonProperty("CommandID")
    public String commandID;
    @JsonProperty("Remark")
    public String remark;
    @JsonProperty("SourceSystem")
    public String sourceSystem;
    @JsonProperty("Version")
    public String version;
    @JsonProperty("Timestamp")
    public Date timestamp;
    public Object payload;
}
