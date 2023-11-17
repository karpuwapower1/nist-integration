package com.nist.integration.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.nist.integration.dto.CpeDto;
import com.nist.integration.service.CpeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cpe")
@RequiredArgsConstructor
public class CpeController {

  private final CpeService cpeService;

  @GetMapping("/{id}")
  public CpeDto getById(@PathVariable("id") String id) {
    return cpeService.getById(id).orElse(null);
  }

  @GetMapping("/ids")
  public List<CpeDto> getByIds(@RequestParam("ids") List<String> ids) {
    return cpeService.getByIds(ids);
  }

  @GetMapping("/names")
  public List<CpeDto> getByNames(@RequestParam("names") List<String> names) {
    return cpeService.getByNames(names);
  }

  @GetMapping("/page")
  public ResponseEntity<Page<CpeDto>> getPage(@RequestParam("name") String name,
                                              @RequestParam("id") String id,
                                              @SortDefault(sort = "cpeNameId") @PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(cpeService.getPage(name, id, pageable));
  }
}
