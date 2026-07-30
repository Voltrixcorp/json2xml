package com.middle.jx.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Value("${middleware.service.xml}")
    private String serviceXmlUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(serviceXmlUrl)
                .defaultHeaders( headers -> {
                    headers.setContentType(MediaType.APPLICATION_XML);
                })
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
