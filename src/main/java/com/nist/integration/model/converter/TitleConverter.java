package com.nist.integration.model.converter;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nist.integration.model.Title;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class  TitleConverter implements AttributeConverter<Set<Title>, String> {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Set<Title> titles) {
    try {
      return objectMapper.writeValueAsString(titles);
    } catch (JsonProcessingException e) {
      return "";
    }
  }

  @Override
  public Set<Title> convertToEntityAttribute(String s) {
    try {
      return objectMapper.readValue(s, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      return Set.of();
    }
  }
}
