package com.khoabeo.quanlyphongkham.security;

import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableWebSecurity

public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Account getAccount = this.accountRepository.findAccountByEmailOrUserName(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new ExceptionNotFound("Not Exits Account "));


        Set<GrantedAuthority> grantedAuthoritySet = getAccount.getRoles().stream().map(
                it -> new SimpleGrantedAuthority( it.getName())
        ).collect(Collectors.toSet());
        System.out.println(grantedAuthoritySet);
        return new User(getAccount.getUserName(), getAccount.getPassword(), grantedAuthoritySet);
    }

}
