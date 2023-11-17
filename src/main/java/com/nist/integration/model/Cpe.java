package com.nist.integration.model;

import java.util.Set;

import com.nist.integration.model.converter.RefConverter;
import com.nist.integration.model.converter.TitleConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cpe {

  @Id
  private String cpeNameId;
  private String cpeName;
  private String lastModified;
  private String created;
  private boolean deprecated;

  @Convert(converter = TitleConverter.class)
  private Set<Title> titles;

  @Convert(converter = RefConverter.class)
  private Set<Ref> refs;
}
