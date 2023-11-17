package com.nist.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfig {

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(5000);
    return restTemplate;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
