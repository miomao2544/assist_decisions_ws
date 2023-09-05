package org.itsci.assist_decisions.controller;

import jakarta.persistence.*;
import org.itsci.assist_decisions.model.*;
import org.itsci.assist_decisions.service.InterestService;
import org.itsci.assist_decisions.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private InterestService interestService;

    @RequestMapping("/test")
    public String test(){
        return "hi";
    }


    @PostMapping("/add")
    public ResponseEntity doRegister(@RequestBody Map<String,String> map){
        try{
           Member member = memberService.doRegister(map);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity doEditProfile(@RequestBody Map<String,String> map){
        try{
            Member member = memberService.doEditProfile(map);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/getProfile/{username}")
    public ResponseEntity getMemberById(@PathVariable("username") String username) throws IllegalStateException{
        try{
            Member member = memberService.getProfile(username);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/uploadimg")
    public ResponseEntity uploadPostImage (@RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
        try {
            String filePath = memberService.uploadMemberImg(file);
            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadimg/{fileName}")
    public byte[] downloadPostImg (@PathVariable("fileName") String fileName) throws IOException {
        byte[] image = Files.readAllBytes(memberService.downloadMemberImg(fileName));
        return image;
    }
}

