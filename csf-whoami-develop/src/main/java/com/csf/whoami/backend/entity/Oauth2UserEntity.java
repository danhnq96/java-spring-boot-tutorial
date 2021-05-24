package com.csf.whoami.backend.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "H01DT_USERS")
@Getter
@Setter
public class Oauth2UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserGroupEntity> userGroups;
}
