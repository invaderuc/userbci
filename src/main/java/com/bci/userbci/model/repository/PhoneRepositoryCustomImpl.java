package com.bci.userbci.model.repository;

import com.bci.userbci.model.entity.Phone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import com.bci.userbci.model.entity.User;

import java.util.List;
import java.util.UUID;

public class PhoneRepositoryCustomImpl implements PhoneRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Phone> getPhonesByUser(UUID userId) {
        return null;
    }

    @Override
    public User getUserByPhone(UUID userId) {
        return null;
    }
}
