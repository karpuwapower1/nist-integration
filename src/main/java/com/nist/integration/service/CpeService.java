package com.nist.integration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nist.integration.dto.CpeDto;
import com.nist.integration.dto.NistResultDto;

public interface CpeService {

  void saveAll(NistResultDto cpeDto);

  Optional<CpeDto> getById(String id);

  List<CpeDto> getByIds(List<String> ids);

  List<CpeDto> getByNames(List<String> names);

  Page<CpeDto> getPage(String name, String id, Pageable pageable);
}
