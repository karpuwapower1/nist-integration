package com.nist.integration.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nist.integration.model.UpdateChangelog;
import com.nist.integration.repository.UpdateChangelogRepository;
import com.nist.integration.service.UpdateChangelogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateChangelogServiceImpl implements UpdateChangelogService {

  private final UpdateChangelogRepository updateChangelogRepository;

  @Override
  public Optional<LocalDateTime> getLast() {
    return updateChangelogRepository.findFirstByOrderByUpdateAsc()
        .map(last -> LocalDateTime.ofInstant(Instant.ofEpochSecond(last.getUpdate()), ZoneId.systemDefault()));
  }

  @Override
  public void save(LocalDateTime localDateTime) {
    updateChangelogRepository.save(
        UpdateChangelog.builder()
            .update(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
            .build()
    );
  }
}
