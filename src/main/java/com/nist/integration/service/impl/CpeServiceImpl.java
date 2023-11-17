package com.nist.integration.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nist.integration.dto.CpeDto;
import com.nist.integration.dto.DeprecateDto;
import com.nist.integration.dto.DeprecateProjection;
import com.nist.integration.dto.NistResultDto;
import com.nist.integration.dto.ProductDto;
import com.nist.integration.model.Cpe;
import com.nist.integration.repository.CpeRepository;
import com.nist.integration.service.CpeService;
import com.nist.integration.service.DeprecateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CpeServiceImpl implements CpeService {

  private final CpeRepository cpeRepository;
  private final DeprecateService deprecateService;

  @Override
  public void saveAll(NistResultDto nistResultDto) {
    List<Cpe> cpes = nistResultDto.getProducts()
        .stream()
        .map(ProductDto::getCpe)
        .map(cpeDto ->
            Cpe.builder()
                .cpeNameId(cpeDto.getCpeNameId())
                .cpeName(cpeDto.getCpeName())
                .lastModified(cpeDto.getLastModified())
                .created(cpeDto.getCreated())
                .deprecated(cpeDto.isDeprecated())
                .titles(cpeDto.getTitles())
                .refs(cpeDto.getRefs())
                .build()
        )
        .toList();

    cpeRepository.saveAll(cpes);
    deprecateService.save(nistResultDto);
  }

  @Override
  public Optional<CpeDto> getById(String id) {
    return cpeRepository.findById(id).map(this::toCpeDto);
  }

  @Override
  public List<CpeDto> getByIds(List<String> ids) {
    return cpeRepository.findAllById(ids)
        .stream()
        .map(this::toCpeDto)
        .toList();
  }

  @Override
  public List<CpeDto> getByNames(List<String> names) {
    return cpeRepository.findAllByCpeNameIn(names)
        .stream()
        .map(this::toCpeDto)
        .toList();

  }

  @Override
  public Page<CpeDto> getPage(String name, String id, Pageable pageable) {
    name = "%" + name + "%";
    id = "%" + id + "%";
    Page<Cpe> pageCpes = cpeRepository.findAllByCpeNameIdLikeAndCpeNameLike(name, id, pageable);

    return new PageImpl<>(pageCpes.getContent()
        .stream()
        .map(this::toCpeDto)
        .toList(),
        pageable,
        pageCpes.getTotalElements());
  }

  private CpeDto toCpeDto(Cpe cpe) {
    return CpeDto.builder()
        .cpeNameId(cpe.getCpeNameId())
        .cpeName(cpe.getCpeName())
        .lastModified(cpe.getLastModified())
        .created(cpe.getCreated())
        .deprecated(cpe.isDeprecated())
        .titles(cpe.getTitles())
        .refs(cpe.getRefs())
        .deprecates(toDeprecateDto(deprecateService.getDeprecates(cpe.getCpeNameId())))
        .deprecatedBy(toDeprecateDto(deprecateService.getDeprecatedBy(cpe.getCpeNameId())))
        .build();
  }

  private Set<DeprecateDto> toDeprecateDto(Set<DeprecateProjection> deprecateProjections) {
    return deprecateProjections.stream().map(projection -> DeprecateDto.builder().cpeNameId(projection.getCpeName()).cpeName(projection.getCpeName()).build())
        .collect(Collectors.toSet());
  }
}
