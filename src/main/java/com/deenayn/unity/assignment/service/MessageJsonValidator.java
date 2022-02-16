package com.deenayn.unity.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.Validator;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class MessageJsonValidator {

    private final Validator validator = Validator.builder().failEarly().build();
    private final Schema schema;

    protected MessageJsonValidator() throws IOException {
        File file = ResourceUtils.getFile("classpath:messageSchema.json");
        try (InputStream inputStream = new FileInputStream(file)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            this.schema = SchemaLoader.load(rawSchema);
        }
    }

    public void validate(String message) throws ValidationException {
        validator.performValidation(schema, new JSONObject(message));
    }
}
