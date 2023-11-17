package com.nist.integration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DeprecateId.class)
public class Deprecate {

  @Id
  private String cpeNameId;

  @Id
  private String deprecatedBy;
}
