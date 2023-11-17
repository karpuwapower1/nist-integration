package com.nist.integration.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nist.integration.dto.DeprecateProjection;
import com.nist.integration.model.Deprecate;
import com.nist.integration.model.DeprecateId;

@Repository
public interface DeprecateRepository extends JpaRepository<Deprecate, DeprecateId> {

  @Query(value ="""
      SELECT c.cpe_name_id AS cpeNameId, c.cpe_name AS cpeName
      from deprecate
      join cpe c on deprecate.cpe_name_id = c.cpe_name_id
      where deprecate.cpe_name_id = :id
    """, nativeQuery = true)
  Set<DeprecateProjection> findDeprecates(@Param("id") String id);

  @Query(value = """
      SELECT c.cpe_name_id AS cpeNameId, c.cpe_name AS cpeName
      from deprecate
      join cpe c on deprecate.deprecated_by = c.cpe_name_id
      where deprecate.deprecated_by = :id
    """, nativeQuery = true)
  Set<DeprecateProjection> findDeprecatedBy(@Param("id") String id);

}
