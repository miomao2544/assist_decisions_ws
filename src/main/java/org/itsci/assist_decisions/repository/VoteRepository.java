package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote,String> {
    @Query(value = "SELECT voteId FROM vote ORDER BY voteId DESC LIMIT 1", nativeQuery = true)
    String getLatestVoteId();


    @Query(value = "SELECT count(*) FROM vote where choiceId = :choiceId", nativeQuery = true)
    public int findVoteForChoice(String choiceId);
}
