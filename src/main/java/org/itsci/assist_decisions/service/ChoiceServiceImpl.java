package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.repository.ChoiceRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChoiceServiceImpl implements ChoiceService{

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Choice> getAllChoices() {
        return choiceRepository.findAll();
    }

    @Override
    public List<Choice> getChoiceById(String postId) {
        return choiceRepository.findByPost_PostId(postId);
    }

    @Override
    public Choice saveChoice(Map<String, String> map) {
        String choiceID = generateChoiceId(choiceRepository.count() +1);
        String choiceName = map.get("choiceName");
        String choiceImage = map.get("choiceImage");
        String postID = map.get("postID");
        Post post = postRepository.getReferenceById(postID);
        Choice choice = new Choice(choiceID,choiceName,choiceImage,post);
        return choiceRepository.save(choice);
    }

    public String generateChoiceId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 11) {
            result = "0" + result;
        }
        result = "C" + result;
        return result;
    }

    @Override
    public Choice updateChoice(Map<String, String> map) {
        return null;
    }

    @Override
    public void deleteChoice(String choiceId) {

    }

}
