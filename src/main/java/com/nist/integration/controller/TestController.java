package com.nist.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nist.integration.job.NIstDataUpdateJob;
import com.nist.integration.job.NistDataFetcherJob;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

  private final NistDataFetcherJob nistDataFetcherJob;
  private final NIstDataUpdateJob nIstDataUpdateJob;

  @GetMapping
  public void getAll() throws Exception {
    nistDataFetcherJob.run(null);
  }

  @GetMapping("/update")
  public void get() throws Exception {
    nIstDataUpdateJob.runUpdate();
  }
}
