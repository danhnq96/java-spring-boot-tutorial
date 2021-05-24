package com.sprint1backend.service.user;

import com.sprint1backend.entity.AppUser;
import com.sprint1backend.model.AppUserDTO;
import com.sprint1backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.sprint1backend.entity.AppUser;
import com.sprint1backend.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAppUserAll() {
        return this.appUserRepository.findAll();
    }

    @Override
    public AppUser findAppUserById(Long id) {
        return this.appUserRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AppUser appUser) {
        this.appUserRepository.save(appUser);
    }

    // convert UserDTO to User entity
    public AppUser convertAppUserDTO(AppUserDTO appUser) {
        AppUser newAppUser = new AppUser();
        newAppUser.setFullName(appUser.getFullName());
        newAppUser.setBirthday(appUser.getBirthday());
        newAppUser.setAddress(appUser.getAddress());
        newAppUser.setEmail(appUser.getEmail());
        newAppUser.setGender(appUser.getGender());
        newAppUser.setPhoneNumber(appUser.getPhoneNumber());
        return newAppUser;
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        return this.appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> testEmailOfAppUser(String emailInput) {
        List<AppUser> appUserList = new ArrayList<>();
        AppUser appUser = this.appUserRepository.findByEmail(emailInput);
        if (appUser != null) {
            appUserList.add(appUser);
        }
        return appUserList;
    }
}



