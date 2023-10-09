package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Ban_Type;
import org.itsci.assist_decisions.model.View_Post;
import org.itsci.assist_decisions.service.MemberService;
import org.itsci.assist_decisions.service.ViewPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/viewposts")
public class ViewPostController {
    @Autowired
    private ViewPostService viewPostService;

    @PostMapping("/add")
    public ResponseEntity addViewPost(@RequestBody Map<String,String> map){
        try{
            View_Post viewPost = viewPostService.saveViewPost(map);
            return new ResponseEntity<>(viewPost, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
