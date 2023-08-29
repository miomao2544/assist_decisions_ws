package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,String> {
}
