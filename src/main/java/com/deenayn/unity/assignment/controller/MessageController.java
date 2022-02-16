package com.deenayn.unity.assignment.controller;

import com.deenayn.unity.assignment.dao.MessageDAO;
import com.deenayn.unity.assignment.model.MessageInfo;
import com.deenayn.unity.assignment.service.KafkaMessageSender;
import com.deenayn.unity.assignment.service.MessageJsonValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.ValidationException;
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
    private final MessageDAO messageDAO;
    private final KafkaMessageSender kafkaMessageSender;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public MessageController(MessageJsonValidator messageJsonValidator, MessageDAO messageDAO, KafkaMessageSender kafkaMessageSender) {
        this.messageJsonValidator = messageJsonValidator;
        this.messageDAO = messageDAO;
        this.kafkaMessageSender = kafkaMessageSender;
    }

    @PostMapping("/")
    public ResponseEntity<Object> postMessage(@RequestBody String message) {
        try {
            messageJsonValidator.validate(message);
            MessageInfo messageInfo = objectMapper.readValue(message, MessageInfo.class);
            messageDAO.save(messageInfo);
            kafkaMessageSender.send(message);
            log.info("Validated and saved message {}", message);
            return ResponseEntity.ok().build();
        } catch (ValidationException e) {
            log.error("Failed to validate json, reason = {}", e.getErrorMessage());
            return ResponseEntity.badRequest().body("Invalid json, reason = " + e.getErrorMessage());
        } catch (Exception e) {
            log.error("Error while sending message, reason = {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Internal error");
        }
    }
}
