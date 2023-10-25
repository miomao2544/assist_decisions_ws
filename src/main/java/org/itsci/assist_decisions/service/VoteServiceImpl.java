package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.*;
import org.itsci.assist_decisions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public List<Vote> getAllVotes() {
        return null;
    }

    @Override
    public Vote getVoteById(String choiceId) {
        return null;
    }

    public int getVoteByChoice(String choiceId) {
        return voteRepository.findVoteForChoice(choiceId);
    }

    public String getChoiceName(String username,String postId) {
        return voteRepository.findChoiceName(username,postId);
    }
    public int getIFVoteChoice(String postId,String username) {
        return voteRepository.findIFVoteChoice(username,postId);
    }
    @Override
    public Vote doVotePost(Map<String, String> map) {
        String rawVoteId = voteRepository.getLatestVoteId();

        if (rawVoteId == null) {
            rawVoteId = "V000000";
        }
        String rawVoteIdWithoutP = rawVoteId.substring(1);
        long rawLongVoteId = Long.parseLong(rawVoteIdWithoutP);
        String voteId = generateVoteId(rawLongVoteId +1);
        Date voteDate = new Date();
        String username = map.get("username");
        String choiceId = map.get("choiceId");
        Member member = memberRepository.getReferenceById(username);
        Choice choice = choiceRepository.getReferenceById(choiceId);
        Vote vote = new Vote(voteId,voteDate,member,choice);
        return voteRepository.save(vote);
    }

    public String generateVoteId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 11) {
            result = "0" + result;
        }
        result = "V" + result;
        return result;
    }
    @Override
    public void updateVote(Vote vote) {

    }

    @Override
    public void deleteVote(String voteId) {

    }
}
