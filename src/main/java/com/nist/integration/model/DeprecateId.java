package com.nist.integration.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeprecateId implements Serializable {

  private String cpeNameId;
  private String deprecatedBy;
}
