package com.bci.userbci.model.dto;

import com.bci.userbci.model.entity.Phone;
import com.bci.userbci.model.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private String name;
    @NotNull
    @Email(message = "Email debe ser valido")
    private String email;
    @NotEmpty(message = "Password es obligatorio")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).+$", message = "Password debe tener mínimo un número y una letra mayuscula")
    @Size(min = 6, max = 64)
    @NotEmpty(message = "El password del usuario es obligatorio")
    private String password;

    private Boolean isActive;

    private LocalDateTime deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Phone> phones;

    public static UserRequest mapToDto(User entity){
        UserRequest dto = new UserRequest();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhones(entity.getPhones());
        dto.setDeleted(entity.getDeleted());
        dto.setIsActive(entity.getIsActive());

        return dto;
    }

    public static User mapToEntity(UserRequest dto){

        User userEntity = new User();
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());

        UUID uuid = UUID.randomUUID();
        userEntity.setUserId(uuid);

        LocalDateTime now = LocalDateTime.now();
        userEntity.setCreated(now);

        List<Phone> phones = dto.getPhones().stream().map(phoneReq -> {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneReq.getPhoneNumber());
            phone.setCityCode(phoneReq.getCityCode());
            phone.setCountryCode(phoneReq.getCountryCode());

            UUID uuidPhone = UUID.randomUUID();
            phone.setPhoneId(uuidPhone);
            phone.setUser(userEntity);
            return phone;
        }).collect(Collectors.toList());

        userEntity.setPhones(phones);
        return userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(phones, that.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phones);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
