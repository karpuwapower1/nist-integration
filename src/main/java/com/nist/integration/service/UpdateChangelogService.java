package com.nist.integration.service;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UpdateChangelogService {

  Optional<LocalDateTime> getLast();

  void save(LocalDateTime end);
}
