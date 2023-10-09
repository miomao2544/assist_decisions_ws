package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.itsci.assist_decisions.model.View_Post;
import org.itsci.assist_decisions.repository.MemberRepository;
import org.itsci.assist_decisions.repository.PostRepository;
import org.itsci.assist_decisions.repository.ViewPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class ViewPostServiceImpl implements ViewPostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ViewPostRepository viewPostRepository;

    @Override
    public View_Post saveViewPost(Map<String, String> map) {
        String rawViewPostId = viewPostRepository.getLatestViewPostId();

        if (rawViewPostId == null) {
            rawViewPostId = "VP00000";
        }
        String rawViewPostIdWithoutP = rawViewPostId.substring(2);
        long rawLongViewPostId = Long.parseLong(rawViewPostIdWithoutP);
        String viewPostId = generateViewPostId(rawLongViewPostId +1);
        Date viewPostDate = new Date();
        String postId = map.get("postId");
        Post post = postRepository.getReferenceById(postId);
        String username = map.get("username");
        Member member = memberRepository.getReferenceById(username);
        View_Post viewPost = new View_Post(viewPostId,viewPostDate,post,member);
        return viewPostRepository.save(viewPost);
    }


    public String generateViewPostId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 10) {
            result = "0" + result;
        }
        result = "VP" + result;
        return result;
    }
}
