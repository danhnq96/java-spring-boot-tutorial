/**
 * Functions list:
 * <p>
 * - Signup
 * - View information
 * - Update infor
 * <p>
 * - Active account
 * - Inactive account
 * - Delete account forever. - Decider.
 */
package com.csf.whoami.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.service.UserService;
import com.csf.whoami.common.domain.SignUpRequestDomain;
import com.csf.whoami.common.domain.UserDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api
@ApiOperation(value = "Các chức năng liên quan đến người dùng.")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @ApiOperation(value = "Phương thức lấy thông tin người dùng hiện tại.")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful message", response = UserDomain.class)})
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserDomain getCurrentUser(Authentication auth) throws Exception {

        UserInfo userdto = (UserInfo) auth.getPrincipal();
        UserDomain user = userService.findById(userdto.getUserId());
        if (user == null) {
            throw new Exception("Can not found User.");
        }
        return user;
    }

    @ApiOperation(value = "Phương thức đăng ký người dùng.")
    @PostMapping("/signup")
    public boolean registerUser(@Valid @RequestBody SignUpRequestDomain signUpRequest)
            throws Exception {

        if (userService.findByUserName(signUpRequest.getUsername()) != null) {
            throw new Exception("Người dùng đã tồn tại.");
        }
        signUpRequest.setPassword(bCryptEncoder.encode(signUpRequest.getPassword()));
        userService.save(signUpRequest);

        return true;
    }
}
