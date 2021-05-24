package com.sprint1backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private AppUserDTO appUser;
}
