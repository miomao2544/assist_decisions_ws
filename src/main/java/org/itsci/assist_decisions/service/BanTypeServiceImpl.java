package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Ban_Type;
import org.itsci.assist_decisions.model.History_Ban;
import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.repository.BanTypeRepository;
import org.itsci.assist_decisions.repository.HistoryBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BanTypeServiceImpl implements BanTypeService{

    @Autowired
    private BanTypeRepository banTypeRepository;
    @Override
    public List<Ban_Type> getAllBanTypes() {
        return banTypeRepository.findAll();
    }

    @Override
    public Ban_Type getBanTypeById(String BanTypeId) {
        return null;
    }

    @Override
    public void saveBanType(Ban_Type BanType) {

    }
    @Override
    public Ban_Type saveBanType(Map<String, String> map) {
        String rawBanTypeId = banTypeRepository.getLatestBanTypeId();

        if (rawBanTypeId == null) {
            rawBanTypeId = "BT00000";
        }
        String rawbanTypeIdWithoutP = rawBanTypeId.substring(2);
        long rawLongBanTypeId = Long.parseLong(rawbanTypeIdWithoutP);
        String banTypeId = generateBanTypeId(rawLongBanTypeId + 1);
        String typeName = map.get("typeName");
        int numberOfDay = Integer.parseInt(map.get("numberOfDay"));
        Ban_Type banType = new Ban_Type(banTypeId,typeName,numberOfDay);
        return banTypeRepository.save(banType);
    }

    public String generateBanTypeId (long rawId) {
        String result = Long.toString(rawId);
        while (result.length() < 10) {
            result = "0" + result;
        }
        result = "BT" + result;
        return result;
    }
    @Override
    public void updateBanType(Ban_Type BanType) {

    }

    @Override
    public void deleteBanType(String BanTypeId) {

    }
}
