package org.itsci.assist_decisions.service;

import jakarta.persistence.*;
import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Comment;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.repository.ChoiceRepository;
import org.itsci.assist_decisions.repository.CommentRepository;
import org.itsci.assist_decisions.repository.MemberRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public List<Comment> getCommentById(String postId) {
        return commentRepository.findCommentByPost_PostIdOrderByCommentIdDesc(postId);
    }

    @Override
    public Comment doCommentPost(Map<String, String> map) {
        String rawCommentId = commentRepository.getLatestCommentId();

        if (rawCommentId == null) {
            rawCommentId = "C000000";
        }
        String rawCommentIdWithoutP = rawCommentId.substring(1);
        long rawLongCommentId = Long.parseLong(rawCommentIdWithoutP);
        String commentId = generateChoiceId(rawLongCommentId +1);
        String comments = map.get("comment");
        Date dateCommentrequestValue = new Date();
        String username = map.get("username");
        String postId = map.get("postId");
        Member member = memberRepository.getReferenceById(username);
        Post post = postRepository.getReferenceById(postId);
        Comment comment = new Comment(commentId,comments,dateCommentrequestValue,member,post);
        return commentRepository.save(comment);
    }
    public String generateChoiceId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 11) {
            result = "0" + result;
        }
        result = "C" + result;
        return result;
    }

    @Override
    public void deleteComment(String choiceId) {

    }
}
