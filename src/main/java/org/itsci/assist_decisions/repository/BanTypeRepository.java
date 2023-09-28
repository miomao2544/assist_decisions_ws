package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Ban_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BanTypeRepository extends JpaRepository<Ban_Type,String> {

    @Query(value = "SELECT banTypeId FROM ban_type ORDER BY banTypeId DESC LIMIT 1", nativeQuery = true)
    String getLatestBanTypeId();
}
