package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.View_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewPostRepository  extends JpaRepository<View_Post,String> {
    @Query(value = "SELECT viewpostId FROM view_post ORDER BY viewpostId DESC LIMIT 1", nativeQuery = true)
    String getLatestViewPostId();
}
