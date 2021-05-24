package com.csf.whoami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.whoami.converter.AdminRoleDomain;
import com.csf.whoami.converter.ConvertAdminRoleDTO;
import com.csf.whoami.database.TbAdminRole;
import com.csf.whoami.repository.AdminRoleRepository;

//@Log4j2
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @Override
    public TbAdminRole findByTbAdminId(Long TbAdminId) {
        TbAdminRole tbAdminRole = adminRoleRepository.findByAdminId(TbAdminId);
        AdminRoleDomain domain = ConvertAdminRoleDTO.dbToDomain(tbAdminRole);
        return domain;
    }
}
