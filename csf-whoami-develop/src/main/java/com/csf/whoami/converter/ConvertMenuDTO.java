package com.csf.whoami.converter;

import com.csf.whoami.database.TbMenu;

public class ConvertMenuDTO {
    public static MenuDomain dbToDomain(TbMenu entity) {

        if (entity == null) {
            return null;
        }

        MenuDomain domain = new MenuDomain();
        domain.setId(entity.getId());
        domain.setMenuName(entity.getMenuName());
        domain.setLinkScreen(entity.getLinkScreen());
        domain.setLocale(entity.getLocale());
        return domain;
    }
}
