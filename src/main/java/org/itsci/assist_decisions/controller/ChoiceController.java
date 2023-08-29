package org.itsci.assist_decisions.controller;


import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/choices")
public class ChoiceController {
    @Autowired
    private ChoiceService choiceService;

    @PostMapping("/add")
    public ResponseEntity addChoice(@RequestBody Map<String,String> map){
        try{
            //Attributes of post from json
            Choice choice = choiceService.saveChoice(map);
            return new ResponseEntity<>(choice, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getChoiceById/{postId}")
    public ResponseEntity getChoiceById(@PathVariable("postId") String postId) throws IllegalStateException{
        try{
            List<Choice> choice = choiceService.getChoiceById(postId);
            return new ResponseEntity<>(choice, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
