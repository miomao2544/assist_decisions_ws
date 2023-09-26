package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Report;
import org.itsci.assist_decisions.model.Vote;

import java.util.List;
import java.util.Map;

public interface VoteService {
    List<Vote> getAllVotes();
    Vote getVoteById(String voteId);
    Vote doVotePost(Map<String, String> map);

    int getVoteByChoice(String choiceId);
    void updateVote(Vote vote);
    void deleteVote(String voteId);
}
