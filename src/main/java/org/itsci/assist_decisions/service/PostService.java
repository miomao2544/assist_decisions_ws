package org.itsci.assist_decisions.service;

import lombok.Data;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> getAllPosts();

    List<Post> getAllPostsDateStopAfter();
    List<Post> getAllPostsForMember(String username);

    List<Post> getAllPostsMe(String username);
    Post getPostById(String postId);

    List<Post> getListPostsInterest(String username);

    List<Post> getListPostByMember(String username);

    List<Post> getPostByPointVoteMember(String username);

    List<Post> getSearchListPostByAll(String title, String interests, String point, String daterequest);
    Integer getListCountMember(String posId);

    Post doAddPost(Map<String, String> map);
    Post doEditPost(Map<String, String> map);
    String uploadPostImg (MultipartFile file) throws IOException;

    Path downloadPostImg (String filePath) ;

    void updateResult(String result,String postId);
    void doDeletePost(String postId);
}
