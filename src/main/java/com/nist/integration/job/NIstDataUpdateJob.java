package com.nist.integration.job;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nist.integration.service.NistService;
import com.nist.integration.service.UpdateChangelogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NIstDataUpdateJob {

  private final NistService nistService;
  private final UpdateChangelogService updateChangelogService;

  @Scheduled(cron = "0 1 * * *")
  public void runUpdate() throws Exception {
    LocalDateTime start = updateChangelogService.getLast().orElseThrow(() ->
        new IllegalArgumentException("Load data before run this job"));
    LocalDateTime end = LocalDateTime.now();
    nistService.fetch(start, end);

    updateChangelogService.save(end);

  }

}
