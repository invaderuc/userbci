package com.bci.userbci.service.impl;

import com.bci.userbci.model.dto.LoginRequest;
import com.bci.userbci.model.dto.LoginResponse;
import com.bci.userbci.model.entity.Phone;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.bci.userbci.model.dto.UserRequest;
import com.bci.userbci.model.entity.User;
import com.bci.userbci.model.repository.UserRepository;
import com.bci.userbci.model.repository.PhoneRepository;
import com.bci.userbci.service.IUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    UserRepository userRepository;
    PhoneRepository phoneRepository;

    public UserServiceImpl(UserRepository userRepository,PhoneRepository phoneRepository){

        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<UserRequest> getUsers() {

        List<User> users = userRepository.getAllUsers();
        List<UserRequest> userDto = new ArrayList<>();
        for (User user: users){
            userDto.add(UserRequest.mapToDto(user));
        }
        return userDto;
    }

    @Override
    public UserRequest getUser(UUID userId) {

        User user = userRepository.getUserById(userId);

        if(user != null){
            UserRequest dto = UserRequest.mapToDto(user);
            return dto;
        }
        return null;
    }
    @Override
    public LoginResponse validarLogin(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if(user != null){
            LoginResponse dto = new LoginResponse();

            dto.setToken(UUID.randomUUID());
            dto.setMessage("Welcome");
            user.setLastLogin(LocalDateTime.now());

            user.setLastLogin(LocalDateTime.now());
            user.setToken(UUID.randomUUID().toString());
            userRepository.save(user);

            return dto;
        }
        return null;
    }


    @Override
    public UserRequest insertUser(UserRequest request) {
        User userEntity = UserRequest.mapToEntity(request);
        userRepository.save(userEntity);
        return request;
    }

    @Override
    public void deleteUser(UUID userId) {

        User user = userRepository.getUserById(userId);

        if(user != null){
            user.setDeleted(LocalDateTime.now());
            user.setIsActive(false);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public User updateUser(UserRequest userRequest) {

        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist."));;
        if(user != null) {
            user.setName(userRequest.getName());

            // Update phones
            Map<UUID, Phone> idToNewPhone = userRequest.getPhones().stream().collect(Collectors.toMap(Phone::getPhoneId, Function.identity()));
            Map<UUID, Phone> idToExistingPhone = user.getPhones().stream().collect(Collectors.toMap(Phone::getPhoneId, Function.identity()));

            // Check for phones that need to be deleted or updated
            List<Phone> phonesToDelete = new ArrayList<>();
            for (UUID id : idToExistingPhone.keySet()) {
                if (idToNewPhone.containsKey(id)) {
                    // Phone needs to be updated
                    idToExistingPhone.get(id).setPhoneNumber(idToNewPhone.get(id).getPhoneNumber());
                } else {
                    // Phone needs to be deleted
                    phonesToDelete.add(idToExistingPhone.get(id));
                }
            }

            // Check for phones that need to be added
            for (UUID id : idToNewPhone.keySet()) {
                if (!idToExistingPhone.containsKey(id)) {
                    // Phone needs to be added
                    Phone newPhone = idToNewPhone.get(id);
                    newPhone.setUser(user);
                    user.getPhones().add(newPhone);
                }
            }

            // Delete phones not present in the updated list
            for (Phone phoneToDelete : phonesToDelete) {
                user.getPhones().remove(phoneToDelete);
                phoneToDelete.setUser(null);
                phoneRepository.delete(phoneToDelete);
            }

            return userRepository.save(user);
        }else{
            return null;
        }
    }
    public UserRequest getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(user != null){
            UserRequest dto = UserRequest.mapToDto(user);
            return dto;
        }
        return null;
    }
}
