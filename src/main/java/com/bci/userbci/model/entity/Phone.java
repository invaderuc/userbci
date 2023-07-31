package com.bci.userbci.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone implements Serializable  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "phone_id", updatable = false, nullable = false)
    private UUID phoneId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotEmpty(message = "El número es Obligatorio")
    @Column(nullable = false)
    private String phoneNumber;
    @NotEmpty(message = "El código ciudad Obligatorio")
    @Column(name="city_code")
    private String cityCode;
    @NotEmpty(message = "El código pais Obligatorio")
    @Column(name="country_code")
    private String countryCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return phoneId == that.phoneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Phone{");
        sb.append("id=").append(phoneId);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public UUID getPhoneId() {
        return phoneId;
    }

    public User getUser() {
        return user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setPhoneId(UUID phoneId) {
        this.phoneId = phoneId;
    }

    public void setUser(User user) { this.user = user;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
