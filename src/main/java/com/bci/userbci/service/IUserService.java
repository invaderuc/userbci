package com.bci.userbci.service;

import com.bci.userbci.model.dto.LoginRequest;
import com.bci.userbci.model.dto.LoginResponse;
import com.bci.userbci.model.dto.UserRequest;
import com.bci.userbci.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<UserRequest> getUsers();
    UserRequest getUser(UUID userId);
    UserRequest insertUser(UserRequest request);
    User updateUser(UserRequest user);
    void deleteUser(UUID userId);
    LoginResponse validarLogin(LoginRequest loginRequest);
}
