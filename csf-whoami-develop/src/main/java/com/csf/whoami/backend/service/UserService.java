/**
 *
 */
package com.csf.whoami.backend.service;

import com.csf.whoami.backend.entity.Oauth2UserEntity;
import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.common.domain.SignUpRequestDomain;
import com.csf.whoami.common.domain.UserDomain;

/**
 * @author mba0051
 *
 */
public interface UserService {

    Oauth2UserEntity findByUserName(String name);

    UserInfo save(SignUpRequestDomain signUpRequest) throws Exception;

    UserDomain findById(String userId);
}
