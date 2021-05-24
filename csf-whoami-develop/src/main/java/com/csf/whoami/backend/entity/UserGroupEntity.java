/**
 *
 */
package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;
import com.csf.whoami.database.TbGroup;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "H05DT_USER_GROUP")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class UserGroupEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USER_ID")
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_S01DT_USER_H05"))
    private Oauth2UserEntity user;

    @Column(name = "GROUP_ID")
    private String groupId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", insertable = false, updatable = false)
//	@ManyToOne(optional = false)
//	@JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_S04ST_ROLE_H05"))
    private TbGroup group;
}
