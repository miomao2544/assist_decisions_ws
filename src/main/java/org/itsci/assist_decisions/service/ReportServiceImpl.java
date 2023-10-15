package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.model.Report;
import org.itsci.assist_decisions.repository.ChoiceRepository;
import org.itsci.assist_decisions.repository.MemberRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.itsci.assist_decisions.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAllReport();
    }

    @Override
    public List<String> getReportCommentByPost(String postId) {
        return reportRepository.findReportCommentByPost(postId);
    }

    @Override
    public String getReportCountByPost(String postId) {
        return reportRepository.findReportCountByPost(postId);
    }

    @Override
    public Report getReportById(String reportId) {

        return reportRepository.getReferenceById(reportId);
    }

    @Override
    public Report doRerortPost(Map<String, String> map) {
        String rawReportId = reportRepository.getLatestReportId();

        if (rawReportId == null) {
            rawReportId = "R000000";
        }
        String rawReportIdWithoutP = rawReportId.substring(1);
        long rawLongReportId = Long.parseLong(rawReportIdWithoutP);
        String reportId = generateReportId(rawLongReportId +1);
        Date reportDate = new Date();
        String reportComment = map.get("reportComment");
        String postId = map.get("postId");
        String username = map.get("username");
        Member member = memberRepository.getReferenceById(username);
        Post post = postRepository.getReferenceById(postId);
        Report report = new Report(reportId,reportDate,reportComment,post,member);
        return reportRepository.save(report);
    }

    public String generateReportId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 11) {
            result = "0" + result;
        }
        result = "R" + result;
        return result;
    }
    @Override
    public void updateReport(Report report) {

    }

    @Override
    public void deleteReport(String reportId) {

    }
}
