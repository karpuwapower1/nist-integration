package com.nist.integration.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nist.integration.client.NistClient;
import com.nist.integration.dto.NistResultDto;
import com.nist.integration.service.CpeService;
import com.nist.integration.service.NistService;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NistServiceImpl implements NistService {

  private final NistClient nistClient;
  private final CpeService cpeService;

  @Override
  public void fetch(@Nullable LocalDateTime start, @Nullable LocalDateTime end) throws InterruptedException {
    NistResultDto resultDto;
    int startIndex = 0;

    do {
      long startTime = System.currentTimeMillis();
      resultDto = nistClient.fetch(startIndex, start, end);
      cpeService.saveAll(resultDto);
      startIndex += nistClient.getResultPerPage();
      makeDelay(startTime);
    } while (startIndex < resultDto.getTotalResults());
  }

  private void makeDelay(long startTime) throws InterruptedException {
    long result = System.currentTimeMillis() - startTime;
    if (result < 6000) {
      Thread.sleep(6000 - result);
    }
  }

}
