package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Post;

import java.util.List;
import java.util.Map;

public interface ChoiceService {
    List<Choice> getAllChoices();
    List<Choice> getChoiceById(String choiceId);

    Choice saveChoice(Map<String, String> map);
    Choice updateChoice(Map<String, String> map);
    void deleteChoice(String choiceId);
}
