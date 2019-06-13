package com.example.sweater.controller;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> errorMapCollector = Collectors.toMap(
                result -> result.getField() + "Error", FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(errorMapCollector);
    }
}
