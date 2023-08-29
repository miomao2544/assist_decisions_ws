package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice,String> {
    public List<Choice> findByPost_PostId(String postId);
}
