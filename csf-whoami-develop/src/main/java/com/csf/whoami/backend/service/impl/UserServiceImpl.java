/**
 *
 */
package com.csf.whoami.backend.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.csf.whoami.backend.entity.Oauth2UserEntity;
import com.csf.whoami.backend.entity.RoleEntity;
import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.entity.UserRoleEntity;
import com.csf.whoami.backend.exception.CustomException;
import com.csf.whoami.backend.exception.ErrorException;
import com.csf.whoami.backend.repository.Oauth2UserRepository;
import com.csf.whoami.backend.repository.RolesRepository;
import com.csf.whoami.backend.repository.UserRoleRepository;
import com.csf.whoami.backend.service.UserService;
import com.csf.whoami.common.constant.DatabaseConstants;
import com.csf.whoami.common.domain.SignUpRequestDomain;
import com.csf.whoami.common.domain.UserDomain;

/**
 * @author mba0051
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Oauth2UserRepository repository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public Oauth2UserEntity findByUserName(String username) {
        return repository.findByUserName(username);
    }

    public UserInfo save(SignUpRequestDomain signUpRequest) throws Exception {
        // Creating user's account
        Oauth2UserEntity user = new Oauth2UserEntity();
//		user.setId(StringUtils.generateUUID());
        user.setUserName(signUpRequest.getUsername());
        user.setUserPassword(signUpRequest.getPassword());
        user = repository.save(user);
        if (user == null) {
            throw new CustomException(ErrorException.CANT_REGIST.getMessage(), ErrorException.CANT_REGIST.getCode(),
                    HttpStatus.BAD_REQUEST);
        }

        // Save role
        RoleEntity role = rolesRepository.findByName(DatabaseConstants.UserRolesConstant.USER.getValue());
        if (role == null) {
            throw new CustomException(ErrorException.CANT_FOUND_USER_ROLE.getMessage(),
                    ErrorException.CANT_FOUND_USER_ROLE.getCode(),
                    HttpStatus.BAD_REQUEST);
        }

        // Save User role.
        UserRoleEntity userRole = new UserRoleEntity();
//		userRole.setId(StringUtils.generateUUID());
        userRole.setUser(user);
        userRole.setRole(role);
        userRole = userRoleRepository.save(userRole);
        if (userRole == null) {
            throw new CustomException(ErrorException.CANT_REGIST.getMessage(), ErrorException.CANT_REGIST.getCode(),
                    HttpStatus.BAD_REQUEST);
        }

        // Convert to DTO.
        UserInfo dto = new UserInfo(user.getId(), user.getUserName(), "",
                Collections.singleton(new SimpleGrantedAuthority(role.getName())));
        return dto;
    }

    @Override
    public UserDomain findById(String userId) {
        Oauth2UserEntity user = repository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        UserDomain domain = new UserDomain();
//		domain.setUserId(user.getId());
//		domain.setUserName(user.getUserName());
        return domain;
    }

}
