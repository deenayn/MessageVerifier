package com.deenayn.unity.assignment.service;

import org.everit.json.schema.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageJsonValidatorTest {

    private MessageJsonValidator validator;

    @BeforeEach
    void setUp() throws IOException {
        validator = new MessageJsonValidator();
    }

    @Test
    void validateValidJson() throws IOException {
        String json = readJsonFromFile("validMessage.json");
        validator.validate(json);
    }

    @Test
    void validateInvalidJsonWithoutTS() throws IOException {
        validateInvalidJson("invalidMessageTs.json");
    }

    @Test
    void validateInvalidJsonNegativeTS() throws IOException {
        validateInvalidJson("invalidMessageNegativeTs.json");
    }

    @Test
    void validateInvalidJsonWithoutSender() throws IOException {
        validateInvalidJson("invalidMessageSender.json");
    }

    @Test
    void validateInvalidJsonWithoutIP() throws IOException {
        validateInvalidJson("invalidMessageIP.json");
    }

    @Test
    void validateInvalidJsonEmptyMessage() throws IOException {
        validateInvalidJson("invalidMessageEmptyMessage.json");
    }

    @Test
    void validateInvalidJsonExtraFields() throws IOException {
        validateInvalidJson("invalidMessageExtraFields.json");
    }

    private void validateInvalidJson(String jsonFile) throws IOException {
        String json = readJsonFromFile(jsonFile);
        assertThrows(ValidationException.class, () -> validator.validate(json));
    }

    private String readJsonFromFile(String fileName) throws IOException {
        return new String(getClass().getClassLoader().getResourceAsStream(fileName).readAllBytes());
    }
}
