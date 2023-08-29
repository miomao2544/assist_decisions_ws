package org.itsci.assist_decisions.service;

import ch.qos.logback.classic.pattern.DateConverter;
import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.repository.InterestRepository;
import org.itsci.assist_decisions.repository.MemberRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService{

    final String mainPath = "C:\\Users\\lovely\\Desktop\\assist_decisions\\imgs\\post\\";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(String postId) {
        return postRepository.getReferenceById(postId);
    }

    @Override
    public Post doAddPost(Map<String, String> map) {

        String rawPostId = postRepository.getLatestPostId();

        if (rawPostId == null) {
            rawPostId = "P000000";
        }
        String rawPostIdWithoutP = rawPostId.substring(1);

        long rawLongPostId = Long.parseLong(rawPostIdWithoutP);

        String postId = generatePostId(rawLongPostId +1);

        String postImage = map.get("postImage");
        String title = map.get("title");
        String description = map.get("description");
        Double postPoint = Double.parseDouble(map.get("postPoint"));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart;
        Date dateStop;
        try {
            dateStart = dateTimeFormat.parse(map.get("dateStart"));
            dateStop = dateTimeFormat.parse(map.get("dateStop"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        String result = map.get("result");
        Integer qtyMax;
        Integer qtyMin;
        try {
            qtyMax = Integer.parseInt(map.get("qtyMax"));
            qtyMin = Integer.parseInt(map.get("qtyMin"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        String username = map.get("username");
        String interestId = map.get("interestId");
        Member member = memberRepository.getReferenceById(username);
        Interest interest = interestRepository.getReferenceById(interestId);
        Post post = new Post(postId,postImage,title,description,postPoint,dateStart,dateStop,result,qtyMax,qtyMin,member,interest);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Map<String, String> map) {
        String postId = map.get("postId");
        String postImage = map.get("postImage");
        String title = map.get("title");
        String description = map.get("description");
        Double postPoint = Double.parseDouble(map.get("postPoint"));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart;
        Date dateStop;
        try {
            dateStart = dateTimeFormat.parse(map.get("dateStart"));
            dateStop = dateTimeFormat.parse(map.get("dateStop"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        String result = map.get("result");
        Integer qtyMax;
        Integer qtyMin;
        try {
            qtyMax = Integer.parseInt(map.get("qtyMax"));
            qtyMin = Integer.parseInt(map.get("qtyMin"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        String username = map.get("username");
        String interestId = map.get("interestId");
        Member member = memberRepository.getReferenceById(username);
        Interest interest = interestRepository.getReferenceById(interestId);
        Post post = new Post(postId,postImage,title,description,postPoint,dateStart,dateStop,result,qtyMax,qtyMin,member,interest);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.getReferenceById(postId);
        postRepository.delete(post);
    }

    @Override
    public String uploadPostImg(MultipartFile file) throws IOException {
        //System.out.println("FILE NAME IS : " + file.getOriginalFilename());
        String newFileName = System.currentTimeMillis() + ".png";
        file.transferTo(new File(mainPath + newFileName));
        return newFileName;
    }

    @Override
    public Path downloadPostImg(String filePath) {
        return new File(mainPath + filePath).toPath();
    }

    public String generatePostId (long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 11) {
            result = "0" + result;
        }
        result = "P" + result;
        return result;
    }
}
