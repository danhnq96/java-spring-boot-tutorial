/**
 *
 */
package com.csf.whoami.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.csf.whoami.backend.service.CommonService;
import com.csf.whoami.common.domain.LoginRequestDomain;

/**
 * @author tuan
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DefaultOAuth2AccessToken checkLogin(LoginRequestDomain domain) throws Exception {

        StringBuilder internalUrl = new StringBuilder();
        internalUrl.append("http://localhost:8080");
        internalUrl.append("/oauth/token");
        String url = internalUrl.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", domain.getUsername());
        map.add("password", domain.getPassword());
        map.add("grant_type", "password");
        map.add("client_id", "whoami-client");
        map.add("client_secret", "whoami-secret");
        map.add("scope", "read");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        try {
            ResponseEntity<DefaultOAuth2AccessToken> authenticate = restTemplate.postForEntity(url, request, DefaultOAuth2AccessToken.class);
            if (authenticate.getStatusCode() == HttpStatus.OK) {
                return authenticate.getBody();
            }
        } catch (HttpClientErrorException ex) {
            throw new Exception(ex.getResponseBodyAsString());
        }
        return null;
    }
}