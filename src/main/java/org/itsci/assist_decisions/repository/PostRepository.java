package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {

    @Query(value = "SELECT p.postid FROM post p ORDER BY p.postid DESC LIMIT 1", nativeQuery = true)
    String getLatestPostId();

    public List<Post> findAllByDateStopAfter(Date date);

    @Query(value = "SELECT * FROM post where interestId in(SELECT interestId FROM memberinterest where username = :username)and username !=:username ", nativeQuery = true)
    public List<Post> findAllByPostsForMember(String username);

    @Query(value = "SELECT * FROM post where username != :username and interestId in(SELECT interestId FROM memberinterest where username = :username) order by postPoint Desc;", nativeQuery = true)
    public List<Post> getListPostsInterest(@Param("username") String username);

    @Query(value = "SELECT * FROM post where username = :username order by dateStop;", nativeQuery = true)
    public List<Post> getListPostByMember(@Param("username") String username);

    @Query(value = "SELECT count(c.postId) FROM choice c right join vote v on c.choiceId = v.choiceId where postId = :postId", nativeQuery = true)
    Integer getListCountMember(@Param("postId") String postId);

    @Query(value = "SELECT p.*,i.interestName FROM post p JOIN interest i ON p.interestId = i.interestId WHERE p.interestId IN :interests AND p.postPoint >= :point AND p.dateStop >= :daterequest AND p.title LIKE %:title%", nativeQuery = true)
    public List<Post> getSearchListPostByAll(@Param("title") String title, @Param("interests") List<String> interests, @Param("point") Integer point, @Param("daterequest") Date daterequest);

    @Transactional
    @Modifying
    @Query(value = "UPDATE post SET result = :result WHERE postId = :postId", nativeQuery = true)
    void updateResult(@Param("result") String result ,@Param("postId")String postId);
}