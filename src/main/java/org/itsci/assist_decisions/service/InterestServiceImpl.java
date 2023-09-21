package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InterestServiceImpl implements InterestService{

    @Autowired
    private InterestRepository interestRepository;
    @Override
    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    @Override
    public List<Interest> InterestsfindByUsername(String username) {
        return interestRepository.InterestsfindByUsername(username);
    }
    @Override
    public Interest getInterestById(String interestId) {
        return interestRepository.getReferenceById(interestId);
    }


    @Override
    public Interest saveInterest(Map<String, String> map) {
        String interestId = generateInterestId(interestRepository.count() + 1);
        String interestName = map.get("interestName");
        Interest interest = new Interest(interestId,interestName);
        return interestRepository.save(interest);
    }

    @Override
    public void updateInterest(Interest interest) {
        interestRepository.save(interest);
    }

    @Override
    public void deleteInterest(String interestId) {
        Interest interest = interestRepository.getReferenceById(interestId);
        interestRepository.delete(interest);
    }

    public String generateInterestId (long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 5) {
            result = "0" + result;
        }
        result = "I" + result;
        return result;
    }
}
