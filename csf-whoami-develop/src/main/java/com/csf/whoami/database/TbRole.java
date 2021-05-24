package com.csf.whoami.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ROLES")
@Where(clause = "deleted_at IS NULL")
public class TbRole extends BaseEntity {

    private static final long serialVersionUID = 1069849621682392744L;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_fullname")
    private String fullName;
}
