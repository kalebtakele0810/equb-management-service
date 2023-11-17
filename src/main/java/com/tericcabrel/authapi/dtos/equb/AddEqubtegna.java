package com.tericcabrel.authapi.dtos.equb;

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
public class AddEqubtegna implements Serializable {
    @JsonProperty("Identifier")
    public String Identifier;
    @JsonProperty("EqubId")
    public String EqubId;

    @JsonProperty("NumberOfTime")
    public int numberOfTime;

    @JsonProperty("Status")
    public String status;
    @JsonProperty("SubGroup")
    public int subGroup;

    @JsonProperty("Channel")
    public String channel;
    @JsonProperty("Amount")
    public int amount;
    @JsonProperty("TransactionId")
    public String transactionId;
}