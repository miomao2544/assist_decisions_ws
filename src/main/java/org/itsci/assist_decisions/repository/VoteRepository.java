package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,String> {
}
