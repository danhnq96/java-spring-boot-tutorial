package com.endgame.adminservice.dto.member;

import com.endgame.adminservice.dto.memberType.MemberTypeDTO;

public class ListMemberDTO {
    private int id;
    private String email;
    private String firstName;
    private String midName;
    private String lastName;
    private String registerDate;
    private Boolean active;
    private MemberTypeDTO memberType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public MemberTypeDTO getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberTypeDTO memberType) {
        this.memberType = memberType;
    }
}
