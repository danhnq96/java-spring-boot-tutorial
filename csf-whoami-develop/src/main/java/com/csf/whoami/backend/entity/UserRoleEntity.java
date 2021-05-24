package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "S03DT_USER_ROLE")
@Where(clause = "deleted_at not null")
@Getter
@Setter
public class UserRoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USER_ID")
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_S03"))
    private Oauth2UserEntity user;

    @Column(name = "ROLE_ID")
    private String roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "FK_S02ST_ROLE_S03"))
    private RoleEntity role;
}
