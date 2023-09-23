package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice,String> {
    public List<Choice> findByPost_PostId(String postId);

    @Query(value = "SELECT choiceId FROM choice ORDER BY choiceId DESC LIMIT 1", nativeQuery = true)
    String getLatestChoiceId();
}
