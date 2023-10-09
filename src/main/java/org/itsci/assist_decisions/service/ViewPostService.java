package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.View_Post;

import java.util.List;
import java.util.Map;
public interface ViewPostService {
    View_Post saveViewPost(Map<String, String> map);
}
