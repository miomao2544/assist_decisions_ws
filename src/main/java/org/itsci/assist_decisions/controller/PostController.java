package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ResponseEntity doAddPost(@RequestBody Map<String,String> map){
        try{
            //Attributes of post from json
            Post post = postService.doAddPost(map);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/update")
    public ResponseEntity doEditPost(@RequestBody Map<String,String> map){
        try{
            //Attributes of post from json
            Post post = postService.doEditPost(map);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/view/{postId}")
    public ResponseEntity getPostById(@PathVariable("postId") String postId) {
        try {
            Post post = postService.getPostById(postId);
            return new ResponseEntity(post, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping("/notify/{username}")
    public ResponseEntity getListPostsInterest(@PathVariable("username") String username) {
        try {
            List<Post> posts = postService.getListPostsInterest(username);
            return new ResponseEntity(posts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listpost/{username}")
    public ResponseEntity getListPostByMember(@PathVariable("username") String username) {
        try {
            List<Post> posts = postService.getListPostByMember(username);
            return new ResponseEntity(posts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/count/{postId}")
    public ResponseEntity getListCountMember(@PathVariable("postId") String postId) {
        try {
            Integer count = postService.getListCountMember(postId);
            System.out.println("-----------------"+ count + "---------------");
            return new ResponseEntity(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/listpreview")
    public ResponseEntity doPreviewPost(){
        try{
            List<Post> posts =  postService.getAllPostsDateStopAfter();
            return new ResponseEntity(posts, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/list/{username}")
    public ResponseEntity doPostInterest(@PathVariable("username") String username) {
        try {
            List<Post> posts = postService.getAllPostsForMember(username);
            return new ResponseEntity(posts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    public ResponseEntity doSearchPost(@RequestParam(value = "title", required = false) String title,
                                       @RequestParam(value = "interests", required = false) String interests,
                                       @RequestParam(value = "point", required = false) String point,
                                       @RequestParam(value = "daterequest", required = false) String daterequest) {
        if (title == null) {
            title = "";
        }
        if (interests == null) {
            interests = "";
        }
        if (point == null || point.isEmpty()) {
            point = "0";
        }
        String daterequestValue;

        if (daterequest != null && !daterequest.isEmpty()) {
            daterequestValue = daterequest;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            daterequestValue = dateFormat.format(new Date());
        }

        try {
            List<Post> posts = postService.getSearchListPostByAll(title, interests, point, daterequestValue);
            return new ResponseEntity(posts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity doDeletePost(@PathVariable("id") String postId) {
        try {
            postService.doDeletePost(postId);
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
