package org.itsci.assist_decisions.service;

import org.itsci.assist_decisions.model.Ban_Type;


import java.util.List;

public interface BanTypeService {
    List<Ban_Type> getAllBanTypes();
    Ban_Type getBanTypeById(String BanTypeId);
    void saveBanType(Ban_Type BanType);
    void updateBanType(Ban_Type BanType);
    void deleteBanType(String BanTypeId);
}
