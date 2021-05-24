package com.endgame.adminservice.sevices.impls;

import com.endgame.adminservice.models.Account;
import com.endgame.adminservice.sevices.AccountService;
import com.endgame.adminservice.dto.authentication.AccountBasicInfoDTO;
import com.endgame.adminservice.dto.authentication.AccountDTOLogin;
import com.endgame.adminservice.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    Account account = new Account();

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        account = accountRepository.findAccountByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("User not found with Email: " + username);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Set<Role> roles = account.getRoles();
//        for(Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            account.setLastLogin(now.toString());
            accountRepository.save(account);
            return new User(account.getUsername(), account.getPassword(), grantedAuthorities);
        }
    }

    @Override
    public AccountBasicInfoDTO getAccountBasicInfo() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(account, AccountBasicInfoDTO.class);
    }

    @Override
    public void resetPassword(AccountDTOLogin accountDTOLogin) {
        Account account = accountRepository.findAccountByUsername(accountDTOLogin.getUsername());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(accountDTOLogin, account);
        accountRepository.save(account);
    }
}
