package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getCommentById(String postId);

    Comment doCommentPost(Map<String, String> map);
    void deleteComment(String choiceId);
}
