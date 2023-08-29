package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAllVotes();
    Vote getVoteById(String voteId);
    void saveVote(Vote vote);
    void updateVote(Vote vote);
    void deleteVote(String voteId);
}
