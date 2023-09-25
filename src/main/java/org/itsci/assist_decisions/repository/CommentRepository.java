package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Comment;
import org.itsci.assist_decisions.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {
    @Query(value = "SELECT commentId FROM comment ORDER BY commentId DESC LIMIT 1", nativeQuery = true)
    String getLatestCommentId();

    public List<Comment> findCommentByPost_PostIdOrderByCommentIdDesc(String postId);
}
