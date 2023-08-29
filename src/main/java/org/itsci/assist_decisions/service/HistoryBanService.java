package org.itsci.assist_decisions.service;



import org.itsci.assist_decisions.model.History_Ban;

import java.util.List;

public interface HistoryBanService {
    List<History_Ban> getAllHistoryBans();
    History_Ban getHistoryBanById(String HistoryBanId);
    void saveHistoryBan(History_Ban HistoryBan);
    void updateHistoryBan(History_Ban HistoryBan);
    void deleteHistoryBan(String HistoryBanId);
}
