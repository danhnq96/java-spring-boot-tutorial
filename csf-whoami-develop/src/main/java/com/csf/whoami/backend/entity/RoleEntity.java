package com.csf.whoami.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "S02ST_ROLE")
@Where(clause = "delflg = 0")
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CODE", nullable = false, length = 20)
    private String code;
}
