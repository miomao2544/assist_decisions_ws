package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(String postId);
    Post doAddPost(Map<String, String> map);
    Post doEditPost(Map<String, String> map);
    String uploadPostImg (MultipartFile file) throws IOException;

    Path downloadPostImg (String filePath) ;
    void doDeletePost(String postId);
}
