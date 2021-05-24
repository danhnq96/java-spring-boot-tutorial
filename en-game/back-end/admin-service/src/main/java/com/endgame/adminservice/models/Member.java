package com.endgame.adminservice.models;

import javax.persistence.*;

@Entity
@Table(name = "Members")
public class Member extends Person {
    @ManyToOne
    @JoinColumn(name = "memberTypeId",  nullable=false)
    private MemberType memberType;

    @Column(name = "lastLogin")
    private String lastLogin;

    @Column(name = "password")
    private String password;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "registerDate")
    private String registerDate;

    @Column(name = "secretQuestion")
    private String secretQuestion;

    @Column(name = "secretAnswer")
    private String secretAnswer;

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
}
