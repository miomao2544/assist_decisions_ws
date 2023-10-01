package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Vote;
import org.itsci.assist_decisions.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService voteService;
    @PostMapping("/add")
    public ResponseEntity doVotePost(@RequestBody Map<String,String> map){
        try{
            Vote vote = voteService.doVotePost(map);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @PostMapping("/votechoice/{choiceId}")
    public ResponseEntity getVoteById(@PathVariable("choiceId") String choiceId) throws IllegalStateException{
        try{
            int vote= voteService.getVoteByChoice(choiceId);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/ifvote/{username}/{postId}")
    public ResponseEntity getIFVoteById(@PathVariable("username") String username,@PathVariable("postId") String postId) throws IllegalStateException{
        try{
            int vote= voteService.getIFVoteChoice(username,postId);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
