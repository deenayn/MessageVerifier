package com.deenayn.unity.assignment.controller;

import com.deenayn.unity.assignment.service.MessageJsonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {

    private final MessageJsonValidator messageJsonValidator;

    public MessageController(MessageJsonValidator messageJsonValidator) {
        this.messageJsonValidator = messageJsonValidator;
    }

    @PostMapping("/")
    public String postMessage(@RequestBody String message) { //TODO change return type from string
        boolean isValid = messageJsonValidator.validate(message);
        return String.valueOf(isValid);
    }
}
