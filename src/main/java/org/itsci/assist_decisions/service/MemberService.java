package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface MemberService {
    List<Member> getAllMembers();
    Member getProfile(String username);
    Member doRegister(Map<String, String> map);
    boolean checkUsernameExists(String username);
    String doLoginMember(String username,String password);
    String uploadMemberImg (MultipartFile file) throws IOException;
    Path downloadMemberImg (String filePath) ;
    Member doEditProfile(Map<String, String> map);
    void deleteMember(String memberId);
}
