package com.nist.integration.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import com.nist.integration.dto.DeprecateProjection;
import com.nist.integration.dto.NistResultDto;
import com.nist.integration.dto.ProductDto;
import com.nist.integration.model.Deprecate;
import com.nist.integration.repository.DeprecateRepository;
import com.nist.integration.service.DeprecateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeprecateServiceImpl implements DeprecateService {

  private final DeprecateRepository deprecateRepository;

  @Override
  public void save(NistResultDto nistResultDto) {
    List<Deprecate> deprecates = nistResultDto.getProducts().stream()
        .map(ProductDto::getCpe)
        .map(cpeDto -> Pair.of(cpeDto.getCpeNameId(), cpeDto.getDeprecates()))
        .flatMap(pair -> pair.getValue().stream().map(deprecateDto -> Deprecate.builder().cpeNameId(deprecateDto.getCpeNameId()).deprecatedBy(pair.getKey()).build()))
        .toList();

    deprecateRepository.saveAll(deprecates);
  }

  @Override
  public Set<DeprecateProjection> getDeprecates(String id) {
    return deprecateRepository.findDeprecates(id);
  }

  @Override
  public Set<DeprecateProjection> getDeprecatedBy(String id) {
    return deprecateRepository.findDeprecatedBy(id);
  }
}
