package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
