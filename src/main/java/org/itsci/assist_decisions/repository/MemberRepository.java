package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<Member,String> {
    @Query(value = "SELECT count(*) FROM assist_decisions_db.member WHERE username = :username", nativeQuery = true)
    String getUsernameUnique(@Param("username") String username);
}
