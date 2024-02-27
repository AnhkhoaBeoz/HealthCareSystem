package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.JWTAuthResponse;
import com.khoabeo.quanlyphongkham.dto.LoginUserDto;
import com.khoabeo.quanlyphongkham.dto.RefreshTokenRequestDTO;
import com.khoabeo.quanlyphongkham.dto.RegisterUserDto;
import com.khoabeo.quanlyphongkham.entity.Account;
import com.khoabeo.quanlyphongkham.entity.RefreshToken;
import com.khoabeo.quanlyphongkham.entity.Role;
import com.khoabeo.quanlyphongkham.jwt.JwtService;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.RoleRepository;
import com.khoabeo.quanlyphongkham.service.AuthService;
import com.khoabeo.quanlyphongkham.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService, AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public JWTAuthResponse login(LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );
        RefreshToken getRefreshTokenByUsername = this.refreshTokenService.findRefreshTokenByUserName(authentication.getName());

        if (getRefreshTokenByUsername != null) {
            this.refreshTokenService.deteleTokenByRefreshToken(getRefreshTokenByUsername);
        }


        if (authentication.isAuthenticated()) {
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(loginUserDto.getUsername());

            String jwtToken = this.jwtService.generateToken(authentication.getName(), roles);

            JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
            jwtAuthResponse.setAccessToken(jwtToken);
            jwtAuthResponse.setRefreshToken(refreshToken.getToken());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtAuthResponse;
        }
        return null;
    }

    @Override
    public JWTAuthResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findRefreshTokenByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccount)
                .map(accountInfo -> {
                    String accessToken = jwtService.generateToken(accountInfo.getUserName(), accountInfo.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
                    return new JWTAuthResponse(accessToken, refreshTokenRequestDTO.getToken());
                })
                .orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));

    }

    @Override
    public String register(RegisterUserDto registerUserDto) {
        Account account = new Account();
        account.setUserName(registerUserDto.getUsername());
        String pass = this.passwordEncoder.encode(registerUserDto.getPassword());
        account.setPassword(pass);
        account.setEmail(registerUserDto.getEmail());
        account.setAvatar(registerUserDto.getAvatar());
        Role userRole = roleRepository.findRoleByName("ROLE_PATIENT");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        account.setRoles(roles);

        this.accountRepository.save(account);
        return "User registered successfully!!!!.";
    }

}
