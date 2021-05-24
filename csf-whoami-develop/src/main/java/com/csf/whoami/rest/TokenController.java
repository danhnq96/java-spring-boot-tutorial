/**
 * Functions list:
 * - revoke token.
 * - Refresh token.
 */
package com.csf.whoami.rest;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(
        value = {"/oauth"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
@Api
public class TokenController {

//    @Autowired
//    private DefaultTokenServices tokenServices;
//
//    @ApiOperation(value = "Phương thức xoá token của người dùng.")
//    @RequestMapping(method = RequestMethod.DELETE, path = "/revoke")
//    @ResponseStatus(HttpStatus.OK)
//    public void revokeToken(Authentication authentication) {
//        final String userToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
//        tokenServices.revokeToken(userToken);
//    }
}
