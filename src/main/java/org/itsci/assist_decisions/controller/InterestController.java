package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/interests")
public class InterestController {
    @Autowired
    private InterestService interestService;

    @RequestMapping("/test")
    public String test(){
        return "hi";
    }


    @PostMapping("/add")
    public ResponseEntity addInterest(@RequestBody Map<String,String> map){
        try{

            Interest interest = interestService.saveInterest(map);
            return new ResponseEntity(interest, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/list")
    public ResponseEntity listInterests(){
        try{
            List<Interest> interests =  interestService.getAllInterests();
            return new ResponseEntity(interests, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
