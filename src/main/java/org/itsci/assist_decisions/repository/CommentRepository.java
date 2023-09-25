package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,String> {
    @Query(value = "SELECT commentId FROM comment ORDER BY commentId DESC LIMIT 1", nativeQuery = true)
    String getLatestCommentId();
}
