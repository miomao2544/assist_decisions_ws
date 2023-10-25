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

    @Query(value = "SELECT c.choiceName FROM choice c JOIN vote v ON c.choiceId = v.choiceId WHERE c.postId = :postId AND v.username = :username", nativeQuery = true)
    public String findChoiceName(String postId,String username);


    @Query(value = "select count(*) from vote where username = :username and :username  in (select username from vote where choiceId in(select choiceId from Choice where postId = :postId));", nativeQuery = true)
    public int findIFVoteChoice(String username,String postId);
}
