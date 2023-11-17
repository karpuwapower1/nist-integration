package com.nist.integration.model.converter;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nist.integration.model.Ref;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public  class RefConverter implements AttributeConverter<Set<Ref>, String> {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Set<Ref> refs) {
      try {
        return objectMapper.writeValueAsString(refs);
      } catch (JsonProcessingException e) {
        return "";
      }
  }

  @Override
  public Set<Ref> convertToEntityAttribute(String s) {
    try {
      return objectMapper.readValue(s, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      return Set.of();
    }
  }
}
