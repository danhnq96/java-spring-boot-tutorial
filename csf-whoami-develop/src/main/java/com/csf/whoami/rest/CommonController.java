/**
 *
 */
package com.csf.whoami.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.backend.service.CommonService;
import com.csf.whoami.common.domain.LoginRequestDomain;
import com.csf.whoami.common.domain.UserDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/common")
@Api
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "Phương thức đăng nhập vào hệ thống.")
    @PostMapping("/login")
    public DefaultOAuth2AccessToken login(@RequestBody LoginRequestDomain domain) throws Exception {
        DefaultOAuth2AccessToken accessToken = null;
        try {
            accessToken = commonService.checkLogin(domain);
        } catch (Exception e) {
            throw new Exception("Login and password invalid.");
        }
        return accessToken;
    }

    private static List<UserDomain> users = new ArrayList<>();
//	private static int initCount = 0;

    @GetMapping
    public List<UserDomain> fetchUserDomain() {
        return users;
    }

    @PostMapping
    public UserDomain addUser(UserDomain user) {
//		String id = "";
//		boolean isDuplicate = false;
//		while(true) {
//			isDuplicate = false;
//			id = "id" + initCount;
//			for(UserDomain item: users) {
//				if (item.getUserId().equalsIgnoreCase(id)) {
//					isDuplicate = true;
//					break;
//				}
//			}
//			if (isDuplicate == false) {
//				break;
//			}
//			++initCount;
//		}

        UserDomain addedUser = new UserDomain();
//		addedUser.setUserId(id);
//		addedUser.setUserName("user name " + initCount);
//		addedUser.setEmail("user" + initCount + "@whoami.edu.vn");
//		addedUser.setRole("USER");
        users.add(addedUser);

        return addedUser;
    }

    @GetMapping("/{id}")
    public UserDomain getUserById(@PathVariable("id") String userId) {
//		for (UserDomain user: users) {
//			if (user.getUserId().equalsIgnoreCase(userId)) {
//				return user;
//			}
//		}

        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUserById(@PathVariable("id") String userId) {
//		UserDomain foundUser = null;
//		for (UserDomain user: users) {
//			if (user.getUserId().equalsIgnoreCase(userId)) {
//				foundUser = user;
//				break;
//			}
//		}

//		if(foundUser != null) {
//			users.remove(foundUser);
//			return Boolean.TRUE;
//		}

        return Boolean.FALSE;
    }
}
