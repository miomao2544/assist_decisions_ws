package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.*;
import org.itsci.assist_decisions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HistoryBanServiceImpl implements HistoryBanService{

    @Autowired
    private HistoryBanRepository historyBanRepository;

    @Autowired
    private BanTypeRepository banTypeRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public List<History_Ban> getAllHistoryBans() {
        return null;
    }

    @Override
    public History_Ban getHistoryBanById(String HistoryBanId) {
        return null;
    }

    @Override
    public History_Ban doBanStatus(Map<String, String> map) {
        String rawhistoryId = historyBanRepository.getLatesthistoryId();

        if (rawhistoryId == null) {
            rawhistoryId = "HB00000";
        }
        String rawhistoryIdWithoutP = rawhistoryId.substring(2);
        long rawLongChoiceId = Long.parseLong(rawhistoryIdWithoutP);
        String historyId = generatehistoryId(rawLongChoiceId +1);
        String banComment = map.get("banComment");
        Date banDate = new Date();
        String banTypeId = map.get("banTypeId");
        Ban_Type bantype = banTypeRepository.getReferenceById(banTypeId);
        String username = map.get("username");
        Member member = memberRepository.getReferenceById(username);
        History_Ban historyBan = new History_Ban(historyId,banComment,banDate,bantype,member);
        return historyBanRepository.save(historyBan);
    }

    public String generatehistoryId(long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 10) {
            result = "0" + result;
        }
        result = "HB" + result;
        return result;
    }

    @Override
    public void updateHistoryBan(History_Ban HistoryBan) {

    }

    @Override
    public void deleteHistoryBan(String HistoryBanId) {

    }
}
