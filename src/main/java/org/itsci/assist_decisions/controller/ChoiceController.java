package org.itsci.assist_decisions.controller;


import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.service.ChoiceService;
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
    @RequestMapping("/uploadimg")
    public ResponseEntity uploadChoiceImage (@RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
        try {
            String filePath = choiceService.uploadChoiceImg(file);
            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadimg/{fileName}")
    public byte[] downloadChoiceImg (@PathVariable("fileName") String fileName) throws IOException {
        byte[] image = Files.readAllBytes(choiceService.downloadChoiceImg(fileName));
        return image;
    }
}
