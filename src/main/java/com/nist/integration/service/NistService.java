package com.nist.integration.service;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;

public interface NistService {

  void fetch(@Nullable LocalDateTime start, @Nullable LocalDateTime end) throws InterruptedException;
}
