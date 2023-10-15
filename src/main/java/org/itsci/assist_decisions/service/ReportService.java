package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Comment;
import org.itsci.assist_decisions.model.Report;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Report> getAllReports();

    List<String> getReportCommentByPost(String postId);
    String getReportCountByPost(String postId);
    Report getReportById(String reportId);
    Report doRerortPost(Map<String, String> map);
    void updateReport(Report report);
    void deleteReport(String reportId);
}
