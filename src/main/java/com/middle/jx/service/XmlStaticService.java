package com.middle.jx.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.middle.jx.model.TranslateResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class XmlStaticService {

    private final RestClient restClient;
    private final XmlMapper xmlMapper = new XmlMapper();

    public XmlStaticService(RestClient restClient) {
        this.restClient = restClient;
    }

    public TranslateResponse callMiddle(String id) {
        String rawResponse = null;
        TranslateResponse response = null;
        try {
            rawResponse = restClient.get()
                    .uri("/" + id + ".xml")
                    .header("Content-Type", "application/xml")
                    .retrieve()
                    .body(String.class);

            response = parseXmlResponse(rawResponse);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("XmlStaticService.callMiddle response callMiddle: {}", rawResponse);
        return response;
    }

    private TranslateResponse parseXmlResponse(String xmlResponse) {
        try {
            return xmlMapper.readValue(xmlResponse, TranslateResponse.class);
        } catch (Exception e) {
            log.error("Error parsing XML response: {}", e.getMessage(), e);
            return null;
        }
    }

}
