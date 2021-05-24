package com.csf.whoami.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoHandler {
    private String username;
    private String privilege;
    private Long companyId;
}
