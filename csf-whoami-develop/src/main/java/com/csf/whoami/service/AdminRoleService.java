package com.csf.whoami.service;

import com.csf.whoami.database.TbAdminRole;

public interface AdminRoleService {
    TbAdminRole findByTbAdminId(Long tbAdminId);
}
