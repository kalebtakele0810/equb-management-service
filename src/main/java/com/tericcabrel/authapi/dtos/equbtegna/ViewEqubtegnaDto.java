package com.tericcabrel.authapi.dtos.equbtegna;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("RequestRefID")
    public String requestRefID;
    @JsonProperty("CommandID")
    public String commandID;
    @JsonProperty("Remark")
    public String remark;
    @JsonProperty("Identifier")
    public String identifier;
}
