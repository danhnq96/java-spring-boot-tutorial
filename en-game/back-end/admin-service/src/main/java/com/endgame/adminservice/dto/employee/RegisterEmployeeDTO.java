package com.endgame.adminservice.dto.employee;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterEmployeeDTO {
    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "First name is less than 30 characters and no special characters")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Pattern(regexp = "[\\p{L}\\s]{0,30}", message = "Mid name is less than 30 characters and no special characters")
    private String midName;

    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "Last name is less than 30 characters and no special characters")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "([+0])(\\d){8,30}", message = "Phone is starting with 0 or +, minimum 9 and maximum 30 characters")
    @NotBlank(message = "Phone is required")
    private String phone;

    @Pattern(regexp = "[\\p{L}\\s\\d.\\-,@/\\\\]{1,500}", message = "The address is less than 190 characters")
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Birthday is required")
    private String birthday;

    @NotBlank(message = "Hire date is required")
    private String startDate;

    @Min(value = 0, message = "The gender must contain 0, 1 or 2")
    @Max(value = 2, message = "The gender must contain 0, 1 or 2")
    private int gender;

    @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))", message = " Email is invalid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "[\\p{L}\\s\\d]{9,20}", message = "Id card is minimum 9 and maximum 20 characters no special characters")
    @NotBlank(message = "Id card is required")
    private String idCard;

    private String image;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public RegisterEmployeeDTO() {
    }

    public RegisterEmployeeDTO(String firstName, String midName, String lastName, String phone, String address, String birthday, String startDate, int gender, String email, String idCard,String image) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.startDate = startDate;
        this.gender = gender;
        this.email = email;
        this.idCard = idCard;
        this.image = image;
    }
}
