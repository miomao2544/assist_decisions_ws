package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Member;
import org.itsci.assist_decisions.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member,String> {
    @Query(value = "SELECT count(*) FROM assist_decisions_db.member WHERE username = :username", nativeQuery = true)
    String getUsernameUnique(@Param("username") String username);

    Member getMemberByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET point = :points WHERE username = :username", nativeQuery = true)
    void updatePointByusername(@Param("username") String username ,@Param("points")int points);

    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET point = point + :points WHERE username = :username", nativeQuery = true)
    void pointVoteByusername(@Param("username") String username ,@Param("points")int points);

    @Query(value = "SELECT username FROM vote where choiceId in (SELECT choiceId FROM choice  where postId = :postId)", nativeQuery = true)
    List<String> getUsernameVotePost(@Param("postId") String postId);
}
