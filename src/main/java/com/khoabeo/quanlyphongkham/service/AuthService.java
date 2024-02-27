package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.JWTAuthResponse;
import com.khoabeo.quanlyphongkham.dto.LoginUserDto;
import com.khoabeo.quanlyphongkham.dto.RefreshTokenRequestDTO;
import com.khoabeo.quanlyphongkham.dto.RegisterUserDto;

public interface AuthService {

    JWTAuthResponse login(LoginUserDto loginUserDto);

    String register(RegisterUserDto registerUserDto);

    public JWTAuthResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
