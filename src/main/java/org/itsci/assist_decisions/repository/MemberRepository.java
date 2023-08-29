package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,String> {

}
