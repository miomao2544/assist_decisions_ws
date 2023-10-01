package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.History_Ban;

import java.util.List;
import java.util.Map;

public interface HistoryBanService {
    List<History_Ban> getAllHistoryBans();

    History_Ban doBanStatus(Map<String, String> map);
    History_Ban getHistoryBanById(String HistoryBanId);

    void updateHistoryBan(History_Ban HistoryBan);

    void updateStatus(String username,String status);
    void deleteHistoryBan(String HistoryBanId);
}
