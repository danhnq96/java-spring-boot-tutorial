/**
 *
 */
package com.csf.whoami.backend.service;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

import com.csf.whoami.common.domain.LoginRequestDomain;

/**
 * @author tuan
 *
 */
public interface CommonService {

    DefaultOAuth2AccessToken checkLogin(LoginRequestDomain domain) throws Exception;

}
