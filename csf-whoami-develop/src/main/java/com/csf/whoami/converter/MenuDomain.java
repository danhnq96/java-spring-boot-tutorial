package com.csf.whoami.converter;

import com.csf.whoami.database.TbMenu;

import lombok.Setter;

public class MenuDomain extends TbMenu {
    private static final long serialVersionUID = -3525100898585888108L;

    @Setter
    private boolean root = false;

    public boolean isRoot() {
        root = getParent() == null;
        return root;
    }
}
