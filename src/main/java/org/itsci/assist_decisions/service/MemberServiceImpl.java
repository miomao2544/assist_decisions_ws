package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.repository.HistoryBanRepository;
import org.itsci.assist_decisions.repository.InterestRepository;
import org.itsci.assist_decisions.repository.MemberRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService{

    final String mainPath = "C:\\Users\\lovely\\Desktop\\assist_decisions\\imgs\\member\\";
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HistoryBanRepository historyRepository;

    @Autowired
    InterestRepository interestRepository;
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getProfile(String username) {
        return memberRepository.getReferenceById(username);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        String result = memberRepository.getUsernameUnique(username);
        return Integer.parseInt(result) > 0;
    }

    @Override
    public String doLoginMember(String username,String Password) {
        String result = "";
        Member member;
         member  = memberRepository.getMemberByUsername(username);
         if(member.getStatus().equals("banned")){
             int numberOfDay = historyRepository.getnumberOfDay(username);
             int daysSinceBan = historyRepository.daysSinceBan(username);
             if (daysSinceBan >numberOfDay){
                 historyRepository.updateStatus(username,"active");
             }
         }
        if(member == null){
            result = "nodata";
        }
        else if (!member.getPassword().equals(hashToMD5(Password))) {
            result = "false";
        }else if (!member.getStatus().equals("active")) {
            result = "noactive";
        } else if (member.getAdminstatus()) {
            result = "admin";
        }
        else if (member.getPassword().equals(hashToMD5(Password))&&member.getStatus().equals("active")) {
            result = "true";
        }
        System.out.println(result);
        return result;
    }


    @Override
    public Member doRegister(Map<String, String> map) {
        String username = map.get("username");
        boolean adminstatus = Boolean.parseBoolean(map.get("adminstatus"));
        String email = map.get("email");
        String firstname = map.get("firstname");
        String gender = map.get("gender");
        String image = map.get("image");
        String lastname = map.get("lastname");
        String nickname = map.get("nickname");
        String password = map.get("password");
        String hashedPassword = hashToMD5(password);
        Integer point = 90;
        String status = "active";
        String tel = map.get("tel");

        Member member = new Member(username, hashedPassword, nickname, gender, firstname, lastname, email, tel, image, status, point, adminstatus);
        String interestsJson = map.get("interests");
        List<String> interestIdList = Arrays.asList(interestsJson.split(","));

        Set<Interest> interests = new HashSet<>();
        for (String id : interestIdList) {
            Interest interest = interestRepository.findById(id.trim()).orElse(null);
            if (interest == null) {
                interest = new Interest(id.trim());
                interestRepository.save(interest);
            }
            interests.add(interest);
        }
        member.setInterests(interests);
        return memberRepository.save(member);
    }


    @Override
    public Member doEditProfile(Map<String, String> map) {
        String username = map.get("username");
        boolean adminstatus = Boolean.parseBoolean(map.get("adminstatus"));
        String email = map.get("email");
        String firstname = map.get("firstname");
        String gender = map.get("gender");
        String image = map.get("image");
        String lastname = map.get("lastname");
        String nickname = map.get("nickname");
        String password = map.get("password");
        String hashedPassword;
        String passUsername = memberRepository.findById(username).get().getPassword();
        System.out.println("passUsername is : "+passUsername);
        System.out.println("hashedPassword is : "+password);
        if(password.equals(passUsername)) {
            hashedPassword = password;

        }else{
            hashedPassword = hashToMD5(password);
        }
        Integer point = Integer.parseInt(map.get("point"));
        String status = map.get("status");
        String tel = map.get("tel");
        Member member = new Member(username, hashedPassword, nickname, gender, firstname, lastname, email, tel, image, status, point, adminstatus);
        String interestsJson = map.get("interests");
        List<String> interestIdList = Arrays.asList(interestsJson.split(","));
        Set<Interest> interests = new HashSet<>();
        for (String id : interestIdList) {
            Interest interest = interestRepository.findById(id.trim()).orElse(null);
            if (interest == null) {
                interest = new Interest(id.trim());
                interestRepository.save(interest);
            }
            interests.add(interest);
        }
        member.setInterests(interests);
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(String username) {
        Member member = memberRepository.getReferenceById(username);
        memberRepository.delete(member);
    }

    @Override
    public void updatePoint(String username,int point) {
        memberRepository.updatePointByusername(username,point);
    }
    @Override
    public void updatePointVote(String username,int point) {
        memberRepository.pointVoteByusername(username,point);
    }

    @Override
    public List<String> getUsernameVotePost(String postId) {
       return  memberRepository.getUsernameVotePost(postId);
    }
    @Override
    public String uploadMemberImg(MultipartFile file) throws IOException {
        String newFileName = System.currentTimeMillis() + ".png";
        file.transferTo(new File(mainPath + newFileName));
        return newFileName;
    }

    @Override
    public Path downloadMemberImg(String filePath) {
        return new File(mainPath + filePath).toPath();
    }



    public static String hashToMD5(String input) {
        try {
            // Get an instance of the MD5 message digest algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert the input string to bytes
            byte[] inputBytes = input.getBytes();

            // Update the message digest with the input bytes
            md.update(inputBytes);

            // Calculate the hash value
            byte[] hashBytes = md.digest();

            // Convert the hash bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
