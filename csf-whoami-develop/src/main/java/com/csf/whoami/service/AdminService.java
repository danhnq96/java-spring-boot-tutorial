package com.csf.whoami.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.csf.whoami.converter.AdminDomain;
import com.csf.whoami.dto.AdminInfo;
import com.csf.whoami.dto.SearchVO;

public interface AdminService extends UserDetailsService {
    AdminDomain findByName(String name);

    AdminDomain findByNameAndPassword(String name, String password);

    Page<AdminInfo> adminList(SearchVO search, Pageable pageable);

    AdminInfo adminDetail(Long id);

    AdminDomain adminFindById(Long id);

    AdminDomain adminUpdate(AdminDomain tbAdmin, AdminInfo info);

    void tokenUpdate(AdminDomain tbadmin);

    AdminDomain findByToken(String token);

    int adminInsert(AdminInfo info);

    int adminModify(AdminInfo info) throws Exception;

    void resetPassword(Long id);

    String getAdminPassword(Long id, String databasekey);
}
