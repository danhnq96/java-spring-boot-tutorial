/**
 *
 */
package com.csf.whoami.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class UserDomain {

    private String userId;
    private String userName;
    @JsonInclude(Include.NON_NULL)
    private String password;
    @JsonInclude(Include.NON_NULL)
    private String role;
    @JsonInclude(Include.NON_NULL)
    private String email;

}
