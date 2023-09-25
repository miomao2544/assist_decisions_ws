package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Comment;
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

//    @PostMapping("/getReportById/{postId}")
//    public ResponseEntity getReportById(@PathVariable("postId") String postId) throws IllegalStateException{
//        try{
//            List<Report> reports = reportService.getReportById(postId);
//            return new ResponseEntity<>(reports, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
