package com.deenayn.unity.assignment.controller;

import com.deenayn.unity.assignment.model.MessageInfo;
import com.deenayn.unity.assignment.service.MessageJsonValidator;
import com.deenayn.unity.assignment.service.sender.PostgresMessageSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {

    private final MessageJsonValidator messageJsonValidator;
    private final PostgresMessageSender postgresMessageSender;

    public MessageController(MessageJsonValidator messageJsonValidator, PostgresMessageSender postgresMessageSender) {
        this.messageJsonValidator = messageJsonValidator;
        this.postgresMessageSender = postgresMessageSender;
    }

    @PostMapping("/")
    public ResponseEntity<Object> postMessage(@RequestBody String message) { //TODO change return type from string
        if (messageJsonValidator.validate(message)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper(); //TODO move to service or pool
                MessageInfo messageInfo = objectMapper.readValue(message, MessageInfo.class);
                postgresMessageSender.send(messageInfo);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                log.error("Error while sending message, reason = {}", e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.badRequest().build();

    }
}
