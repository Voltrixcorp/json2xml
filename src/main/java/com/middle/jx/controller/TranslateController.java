package com.middle.jx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.middle.jx.model.ErrorResponse;
import com.middle.jx.model.TranslateResponse;
import com.middle.jx.service.XmlStaticService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TranslateController {

    private final ObjectMapper objectMapper;
    private final XmlStaticService xmlStaticService;

    public TranslateController(ObjectMapper objectMapper, XmlStaticService xmlStaticService) {
        this.objectMapper = objectMapper;
        this.xmlStaticService = xmlStaticService;
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<String> companies(@PathVariable String id) {
        log.info("Request company by {}", id);

        if (!isInRange(id)) {
            return buildErrorResponse("Not Found", "Company ID " + id + " is out of range");
        }

        TranslateResponse srvResponse = xmlStaticService.callMiddle(id);
        log.info("Response company by {}", id);
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(srvResponse));
        } catch (JsonProcessingException e) {
            log.error("Error serializing response", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to serialize response");
        }
    }

    private ResponseEntity<String> buildErrorResponse(String error, String description) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(error);
        errorResponse.setError_description(description);
        try {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectMapper.writeValueAsString(errorResponse));
        } catch (JsonProcessingException e) {
            log.error("Error serializing error response", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Not Found\",\"error_description\":\"Unable to serialize error response\"}");
        }
    }

    private Boolean isInRange(String id) {
        boolean isCheck = false;
        int checkId = Integer.parseInt(id);
        if (checkId <= 2 && checkId >= 1) {
            isCheck = true;
        }
        return isCheck;
    }
}
