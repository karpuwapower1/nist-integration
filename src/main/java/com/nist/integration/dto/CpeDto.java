package com.nist.integration.dto;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nist.integration.model.Ref;
import com.nist.integration.model.Title;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpeDto {

  private String cpeNameId;
  private String cpeName;
  private String lastModified;
  private String created;
  private boolean deprecated;

  @Default
  private Set<Title> titles = new HashSet<>();

  @Default
  private Set<Ref> refs = new HashSet<>();

  @Default
  private Set<DeprecateDto> deprecatedBy = new HashSet<>();

  private Set<DeprecateDto> deprecates = new HashSet<>();
}
