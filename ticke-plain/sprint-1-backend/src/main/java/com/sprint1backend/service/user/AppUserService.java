package com.sprint1backend.service.user;

import com.sprint1backend.entity.AppUser;
import com.sprint1backend.model.AppUserDTO;

import java.util.List;

public interface AppUserService {
    List<AppUser> findAppUserAll();

    AppUser findAppUserById(Long id);

    void save(AppUser appUser);

    AppUser findAppUserByEmail(String email);
    public AppUser convertAppUserDTO(AppUserDTO appUser);

    List<AppUser> testEmailOfAppUser(String emailInput);
}
