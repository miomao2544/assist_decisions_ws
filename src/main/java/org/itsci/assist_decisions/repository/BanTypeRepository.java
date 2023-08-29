package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Ban_Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanTypeRepository extends JpaRepository<Ban_Type,String> {
}
