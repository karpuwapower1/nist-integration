package com.nist.integration.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nist.integration.model.Cpe;

@Repository
public interface CpeRepository extends JpaRepository<Cpe, String> {

  Set<Cpe> findAllByCpeNameIn(List<String> names);

  Page<Cpe> findAllByCpeNameIdLikeAndCpeNameLike(String cpeNameId, String cpeName, Pageable pageable);
}
