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
@Table(name = "H12DT_NOTIFICATIONS")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class NotificationsEntity extends BaseEntity {

    private static final long serialVersionUID = -1645873207508848856L;

    @Column(name = "NOTIFY_TYPE")
    private String notifyType;
    @Column(name = "NOTIFY_LINK")
    private String notifyLink;
    @Column(name = "NOTIFY_STATUS")
    private String notifyStatus;
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Column(name = "INVITE_FROM")
    private String inviteFrom;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVITE_FROM", insertable = false, updatable = false)
//	@OneToOne(optional = true)
//	@JoinColumn(name = "INVITE_FROM", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H12_2"))
    private Oauth2UserEntity invite;

    @Column(name = "INVITE_TO")
    private String inviteTo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVITE_TO", insertable = false, updatable = false)
//	@OneToOne(optional = true)
//	@JoinColumn(name = "INVITE_TO", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H12"))
    private Oauth2UserEntity beInvited;
}
