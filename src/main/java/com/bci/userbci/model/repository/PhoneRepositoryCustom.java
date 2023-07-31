package com.bci.userbci.model.repository;
import com.bci.userbci.model.entity.Phone;
import com.bci.userbci.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface PhoneRepositoryCustom {

    List<Phone> getPhonesByUser(UUID userId);
    User getUserByPhone(UUID userId);
}
