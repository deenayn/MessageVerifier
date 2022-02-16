package com.deenayn.unity.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageInfo {
    private int ts;
    private String sender;
    private JsonNode message;
    @JsonProperty("sent-from-ip")
    private String sentFromIp;
    private Integer priority;
}
