package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Report;

import java.util.List;

public interface ReportService {
    List<Report> getAllReports();
    Report getReportById(String reportId);
    void saveReport(Report report);
    void updateReport(Report report);
    void deleteReport(String reportId);
}
