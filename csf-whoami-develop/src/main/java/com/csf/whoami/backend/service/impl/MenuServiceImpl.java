package com.csf.whoami.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.whoami.backend.repository.MenuRepository;
import com.csf.whoami.backend.service.MenuService;
import com.csf.whoami.converter.ConvertMenuDTO;
import com.csf.whoami.converter.MenuDomain;
import com.csf.whoami.database.TbMenu;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuDomain> getMenus() {
        List<TbMenu> menus = menuRepository.findAll();
        return menus.stream().map(mnu -> ConvertMenuDTO.dbToDomain(mnu)).collect(Collectors.toList());
    }
}
