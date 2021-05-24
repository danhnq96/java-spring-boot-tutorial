package com.endgame.adminservice.dto.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ListEmployeeDTO {
    private int id;
    private String firstName;
    private String midName;
    private String lastName;
    private String address;
    private String birthday;
    private String image;
    private Boolean active;
    private String phone;

    private AccountDTO account;


    public ListEmployeeDTO(int id, String firstName, String midName, String lastName, String address, String birthday, String image, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.image = image;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
