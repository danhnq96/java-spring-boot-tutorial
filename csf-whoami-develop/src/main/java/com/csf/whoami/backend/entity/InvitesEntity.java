/**
 *
 */
package com.csf.whoami.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "H13DT_INVITES")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class InvitesEntity extends BaseEntity {

    private static final long serialVersionUID = -4446409870535795984L;

    @Column(name = "INVITE_TYPE")
    private String inviteType;

    @Column(name = "OWNER")
    private String ownerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER", insertable = false, updatable = false)
//	@OneToOne(optional = true)
//	@JoinColumn(name = "OWNER", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H13"))
    private Oauth2UserEntity owner;

    @Column(name = "BE_INVITED")
    private String invitedId;
    //	@OneToOne(optional = true)
//	@JoinColumn(name = "BE_INVITED", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H13_2"))
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BE_INVITED", insertable = false, updatable = false)
    private Oauth2UserEntity beInvited;

    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DETAIL_ID")
    private String detailId;

    @Column(name = "CONDITION")
    private String condition = null;
}
