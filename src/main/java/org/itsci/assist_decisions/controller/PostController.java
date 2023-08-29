package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.service.PostService;
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
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/test")
    public String test(){
        return "hi";
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody Map<String,String> map){
        try{
            //Attributes of post from json
            Post post = postService.savePost(map);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/update")
    public ResponseEntity updatePost(@RequestBody Map<String,String> map){
        try{
            //Attributes of post from json
            Post post = postService.updatePost(map);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity listPost(){
        try{
            List<Post> posts =  postService.getAllPosts();
            return new ResponseEntity(posts, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePostByPostId (@PathVariable("id") String postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>("Post ID " + postId + " was deleted!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/uploadimg")
    public ResponseEntity uploadPostImage (@RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
        try {
            String filePath = postService.uploadPostImg(file);
            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadimg/{fileName}")
    public byte[] downloadPostImg (@PathVariable("fileName") String fileName) throws IOException {

        byte[] image = Files.readAllBytes(postService.downloadPostImg(fileName));
        return image;
    }

}
