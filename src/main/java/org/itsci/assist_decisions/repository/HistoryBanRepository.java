package org.itsci.assist_decisions.repository;

import org.itsci.assist_decisions.model.History_Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryBanRepository extends JpaRepository<History_Ban,String> {

    @Query(value = "SELECT historyId FROM history_ban ORDER BY historyId DESC LIMIT 1", nativeQuery = true)
    String getLatesthistoryId();
}
