package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.History_Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HistoryBanRepository extends JpaRepository<History_Ban,String> {

    @Query(value = "SELECT historyId FROM history_ban ORDER BY historyId DESC LIMIT 1", nativeQuery = true)
    String getLatesthistoryId();

    @Query(value = "SELECT numberOfDay FROM ban_type WHERE banTypeId = (SELECT banTypeId FROM history_ban WHERE username = :username ORDER BY banDate DESC LIMIT 1);", nativeQuery = true)
    int getnumberOfDay(String username);

    @Query(value = "SELECT DATEDIFF(NOW(), banDate) AS daysSinceBan FROM history_ban WHERE username = :username ORDER BY banDate DESC LIMIT 1", nativeQuery = true)
    int daysSinceBan(String username);
    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET status = :status WHERE username = :username", nativeQuery = true)
    void updateStatus(@Param("username") String username , @Param("status")String status);
}
