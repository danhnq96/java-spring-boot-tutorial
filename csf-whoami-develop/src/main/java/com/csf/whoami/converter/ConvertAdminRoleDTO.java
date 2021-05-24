package com.csf.whoami.converter;

import com.csf.whoami.database.TbAdminRole;

public class ConvertAdminRoleDTO {

    public static AdminRoleDomain dbToDomain(TbAdminRole entity) {

        if (entity == null) {
            return null;
        }

        AdminRoleDomain domain = new AdminRoleDomain();
        domain.setId(entity.getId());
        domain.setAdminId(entity.getAdminId());
        domain.setRoleId(entity.getRoleId());
        return domain;
    }
}
