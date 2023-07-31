package com.bci.userbci.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bci.userbci.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
}
