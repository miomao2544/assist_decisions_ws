package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {

    @Query(value = "SELECT p.postid FROM post p ORDER BY p.postid DESC LIMIT 1", nativeQuery = true)
    String getLatestPostId();

    public List<Post> findAllByDateStopAfter(Date date);


}