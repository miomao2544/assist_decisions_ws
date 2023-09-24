package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest,String> {
    @Query(value = "SELECT * FROM interest where interestId in(SELECT interestId FROM memberinterest where username = :username)", nativeQuery = true)
    public List<Interest> InterestsfindByUsername(@Param("username") String username);
}
