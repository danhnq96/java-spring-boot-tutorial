package com.endgame.adminservice.sevices;

import com.endgame.adminservice.dto.authentication.AccountBasicInfoDTO;
import com.endgame.adminservice.dto.authentication.AccountDTOLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface AccountService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    AccountBasicInfoDTO getAccountBasicInfo();
    void resetPassword(AccountDTOLogin accountDTOLogin);
}
