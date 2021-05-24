package com.sprint1backend.service.app_admin;

import com.sprint1backend.entity.AppAdmin;
import com.sprint1backend.repository.AppAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppAdminServiceImpl implements AppAdminService {

    @Autowired
    private AppAdminRepository appAdminRepository;

    @Override
    public AppAdmin findById(Long id) {
        return this.appAdminRepository.findById(id).orElse(null);
    }

}
