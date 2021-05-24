package com.csf.whoami.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_GROUPS")
@Where(clause = "deleted_at IS NULL")
@Getter
@Setter
public class TbGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "GROUP_TYPE")
    private String groupType;

    @Column(name = "GROUP_OWNER")
    private String groupOwner;

    @Column(name = "IS_PUBLISH")
    private char isPublish;

    @Column(name = "IS_CLOSED")
    private char isClosed;

    @Column(name = "IS_PRIVATE")
    private char isPrivate;

    @Column(name = "IS_LOCK")
    private char isLock;

    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;

    @Column(name = "GROUP_URL")
    private String groupUrl;
}
