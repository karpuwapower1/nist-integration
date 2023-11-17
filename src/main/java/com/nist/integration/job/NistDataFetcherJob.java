package com.nist.integration.job;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nist.integration.service.NistService;
import com.nist.integration.service.UpdateChangelogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NistDataFetcherJob implements ApplicationRunner {

  private final NistService nistService;
  private final UpdateChangelogService updateChangelogService;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (updateChangelogService.getLast().isEmpty()) {
      LocalDateTime end = LocalDateTime.now();
      nistService.fetch(null, end);
      updateChangelogService.save(end);
    }
  }
}
