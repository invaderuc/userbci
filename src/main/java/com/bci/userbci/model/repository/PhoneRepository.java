package com.bci.userbci.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bci.userbci.model.entity.Phone;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID>, PhoneRepositoryCustom {
}
