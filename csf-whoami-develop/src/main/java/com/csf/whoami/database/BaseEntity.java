package com.csf.whoami.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.csf.whoami.common.utilities.AuthenticationUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
abstract public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date deletedAt;

    /**
     * Preparing before insert.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = null;
    }

    /**
     * Prepare data before update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        updatedBy = AuthenticationUtils.getUser().getId();
    }

    @PreRemove
    protected void onRemove() {
        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = new Date();
    }
}
