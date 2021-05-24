package com.csf.whoami.converter;

import com.csf.whoami.database.TbAdmin;

public class ConvertAdminDTO {

    public static AdminDomain dbToDomain(TbAdmin entity) {

        if (entity == null) {
            return null;
        }

        AdminDomain domain = new AdminDomain();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setToken(entity.getToken());
        return domain;
    }
}
