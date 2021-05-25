package com.technologyplan.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BasicEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @Column(name = "UPDATED_BY")
    protected Long updatedBy;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date deletedAt;

    /**
     * Preparing before insert.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
//        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = null;
    }

    /**
     * Prepare data before update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
//        updatedBy = AuthenticationUtils.getUser().getId();
    }

    /**
     * Prepare data before delete
     */
    @PreRemove
    protected void onRemove() {
//        updatedBy = AuthenticationUtils.getUser().getId();
        deletedAt = new Date();
    }

}
