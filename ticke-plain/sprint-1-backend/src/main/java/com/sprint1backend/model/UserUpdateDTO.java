package com.sprint1backend.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserUpdateDTO {
    private Long id;
    @NotEmpty(message = "Vui lòng nhập họ tên")
    @Size(min = 10,max = 30,message = "Họ tên không hợp lệ")
    private String fullName;
    private String email;
    @NotEmpty(message = "Vui lòng nhập ngày sinh")
    @Pattern(regexp = "/^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))/",message = "Ngày sinh không đúng định dạng dd/mm/yyyy")
    private LocalDate birthday;
    private String userRank;
    private Boolean gender;
    @NotEmpty(message = "Vui lòng nhập số điện thoại")
    @Pattern(regexp = "/^0[35789]\\d{8}$/", message = "Số điện thoại không đúng định dạng.")
    @Pattern(regexp = "/^[^\\d]+$/", message = "Số điện thoại phải là số.")
    private String phoneNumber;
    private String address;
    private String avatarImageUrl;

    public UserUpdateDTO( String fullName, String email, LocalDate birthday, String userRank, Boolean gender, String phoneNumber, String address) {
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.userRank = userRank;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
    }
}
