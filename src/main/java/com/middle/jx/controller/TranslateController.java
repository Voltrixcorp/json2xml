package com.middle.jx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.middle.jx.model.TranslateResponse;
import com.middle.jx.service.XmlStaticService;
import lombok.extern.slf4j.Slf4j;

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
        TranslateResponse srvResponse = null;
        log.info("Request company by {}", id);

        if (isInRange(id)) {
            srvResponse = xmlStaticService.callMiddle(id);
        } else {
            srvResponse = new TranslateResponse();
            srvResponse.setDescription("Range outside of allowed values");
        }

        log.info("Response company by {}", id);
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(srvResponse));
        } catch (JsonProcessingException e) {
            log.error("Error serializing response", e);
            return ResponseEntity.status(500).body("Unable to serialize response");
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
