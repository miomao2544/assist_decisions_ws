package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report,String> {

    @Query(value = "SELECT reportId FROM report ORDER BY reportId DESC LIMIT 1", nativeQuery = true)
    String getLatestReportId();
}
