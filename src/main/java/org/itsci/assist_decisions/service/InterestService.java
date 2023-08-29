package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Interest;

import java.util.List;
import java.util.Map;

public interface InterestService {
    List<Interest> getAllInterests();
    Interest getInterestById(String interestId);
    Interest saveInterest(Map<String, String> map);
    void updateInterest(Interest interest);
    void deleteInterest(String interestId);
}
