package com.bci.userbci.model.repository;

import com.bci.userbci.model.dto.UserRequest;
import com.bci.userbci.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {

    List<User> getAllUsers();
    User findByEmail(String email);
    User getUserById(UUID userId);
}
