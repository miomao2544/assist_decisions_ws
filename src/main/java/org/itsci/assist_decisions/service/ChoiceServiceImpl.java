package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.repository.ChoiceRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChoiceServiceImpl implements ChoiceService{
    final String mainPath = "C:\\Users\\lovely\\Desktop\\assist_decisions\\imgs\\choice\\";
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
        String rawChoiceId = choiceRepository.getLatestChoiceId();

        if (rawChoiceId == null) {
            rawChoiceId = "C000000";
        }
        String rawChoiceIdWithoutP = rawChoiceId.substring(1);
        long rawLongChoiceId = Long.parseLong(rawChoiceIdWithoutP);
        String choiceId = generateChoiceId(rawLongChoiceId +1);
        String choiceName = map.get("choiceName");
        String choiceImage = map.get("choiceImage");
        String postId = map.get("postId");
        Post post = postRepository.getReferenceById(postId);
        Choice choice = new Choice(choiceId,choiceName,choiceImage,post);
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

    @Override
    public String uploadChoiceImg(MultipartFile file) throws IOException {
        String newFileName = System.currentTimeMillis() + ".png";
        file.transferTo(new File(mainPath + newFileName));
        return newFileName;
    }

    @Override
    public Path downloadChoiceImg(String filePath) {
        return new File(mainPath + filePath).toPath();
    }
}
