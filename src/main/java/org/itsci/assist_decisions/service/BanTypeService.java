package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Ban_Type;
import org.itsci.assist_decisions.model.Interest;


import java.util.List;
import java.util.Map;

public interface BanTypeService {
    List<Ban_Type> getAllBanTypes();
    Ban_Type getBanTypeById(String BanTypeId);

    Ban_Type saveBanType(Map<String, String> map);
    void saveBanType(Ban_Type BanType);
    void updateBanType(Ban_Type BanType);
    void deleteBanType(String BanTypeId);
}
