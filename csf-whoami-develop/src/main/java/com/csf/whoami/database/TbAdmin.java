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
@Table(name = "TB_ADMINS")
@Where(clause = "deleted_at IS NULL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TbAdmin extends BaseEntity {

    private static final long serialVersionUID = -3445707168063012262L;

    @Column(name = "admin_name")
    private String fullName;

    @Column(name = "admin_username")
    private String name;

    @Column(name = "admin_email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "token")
    private String token;
}