package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.repository.InterestRepository;
import org.itsci.assist_decisions.repository.MemberRepository;
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
        Integer point = Integer.parseInt(map.get("point"));
        String status = map.get("status");
        String tel = map.get("tel");
        String interestId = map.get("interestId");

        System.out.println(username);

        Member member = new Member(username, hashedPassword, nickname, gender, firstname, lastname, email, tel, image, status, point, adminstatus);

        String[] interestIdsArray = interestId.split(",");
        Set<Interest> interests = new HashSet<>();
        for (String id : interestIdsArray) {
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
        String hashedPassword = hashToMD5(password);
        Integer point = Integer.parseInt(map.get("point"));
        String status = map.get("status");
        String tel = map.get("tel");
        String interestId = map.get("interestId");
        System.out.println(username);
        Member member = new Member(username,hashedPassword,nickname,gender,firstname,lastname,email,tel,image,status,point,adminstatus);
        Interest interest = interestRepository.getReferenceById(interestId);
        member.getInterests().add(interest);
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(String username) {
        Member member = memberRepository.getReferenceById(username);
        memberRepository.delete(member);
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


    public String generateMemberId (long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 6) {
            result = "0" + result;
        }
        result = "Member" + result;
        return result;
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
