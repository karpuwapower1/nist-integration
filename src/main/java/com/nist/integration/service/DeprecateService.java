package com.nist.integration.service;

import java.util.Set;

import com.nist.integration.dto.DeprecateProjection;
import com.nist.integration.dto.NistResultDto;

public interface DeprecateService {

  void save(NistResultDto nistResultDto);

  Set<DeprecateProjection> getDeprecates(String id);

  Set<DeprecateProjection> getDeprecatedBy(String id);
}
