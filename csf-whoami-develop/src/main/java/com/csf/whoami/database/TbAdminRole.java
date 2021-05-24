package com.csf.whoami.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ADMIN_ROLE")
@Where(clause = "deleted_at IS NULL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TbAdminRole extends BaseEntity {

    private static final long serialVersionUID = 8388614359578517293L;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "role_id")
    private Long roleId;
}