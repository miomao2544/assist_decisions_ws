package org.itsci.assist_decisions.controller;


import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Comment;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity doCommentPost(@RequestBody Map<String,String> map){
        try{
            Comment comment = commentService.doCommentPost(map);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
