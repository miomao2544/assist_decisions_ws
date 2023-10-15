package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Comment;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.model.Report;
import org.itsci.assist_decisions.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    public ResponseEntity doReportPost(@RequestBody Map<String,String> map){
        try{
            Report report = reportService.doRerortPost(map);
            return new ResponseEntity<>(report, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/list")
    public ResponseEntity getListReport(){
        try{
            List<Report> reports =  reportService.getAllReports();
            return new ResponseEntity(reports, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getReportById/{reportId}")
    public ResponseEntity getReportById(@PathVariable("reportId") String reportId) throws IllegalStateException{
        try{
            Report report = reportService.getReportById(reportId);
            return new ResponseEntity<>(report, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comments/{postId}")
    public ResponseEntity getReportCommentByPost(@PathVariable("postId") String postId) throws IllegalStateException{
        try{
            List<String> reportComments = reportService.getReportCommentByPost(postId);
            return new ResponseEntity<>(reportComments, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/count/{postId}")
    public ResponseEntity getReportCountByPost(@PathVariable("postId") String postId) throws IllegalStateException{
        try{
            String count = reportService.getReportCountByPost(postId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
