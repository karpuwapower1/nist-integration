package com.nist.integration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nist.integration.model.UpdateChangelog;

@Repository
public interface UpdateChangelogRepository extends JpaRepository<UpdateChangelog, Long> {

  Optional<UpdateChangelog> findFirstByOrderByUpdateAsc();
}
