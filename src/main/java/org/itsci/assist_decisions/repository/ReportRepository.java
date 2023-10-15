package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,String> {

    @Query(value = "SELECT reportId FROM report ORDER BY reportId DESC LIMIT 1", nativeQuery = true)
    String getLatestReportId();

    @Query(value = "SELECT * FROM report WHERE (postId, reportId) IN (SELECT postId, MAX(reportId) AS reportId FROM report GROUP BY postId) ORDER BY postId", nativeQuery = true)
     List<Report> findAllReport();

    @Query(value = "SELECT reportComment FROM report WHERE postId = :postId", nativeQuery = true)
    List<String> findReportCommentByPost(String postId);

    @Query(value = "SELECT COUNT(*) FROM report WHERE postId =  :postId", nativeQuery = true)
    String findReportCountByPost(String postId);
}
