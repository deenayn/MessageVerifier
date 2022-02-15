package com.deenayn.unity.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //TODO is needed here?
public class Message { //TODO no extra fields
    private int ts; //TODO valid unix timestamp
    private String sender;
    private MessageContent message; //TODO present + at least one field
    @JsonProperty("sent-from-ip")
    private String sentFromIp; //if present - valid ipv4 address
    private Integer priority;

    static class MessageContent {
        private String foo;
        private String baz;
    }
}
