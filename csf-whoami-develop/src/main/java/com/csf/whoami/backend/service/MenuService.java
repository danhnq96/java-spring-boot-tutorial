package com.csf.whoami.backend.service;

import java.util.List;

import com.csf.whoami.converter.MenuDomain;

public interface MenuService {
    public List<MenuDomain> getMenus();
}
