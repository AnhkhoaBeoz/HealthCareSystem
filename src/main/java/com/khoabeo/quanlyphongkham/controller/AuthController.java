package com.khoabeo.quanlyphongkham.controller;


import com.khoabeo.quanlyphongkham.dto.JWTAuthResponse;
import com.khoabeo.quanlyphongkham.dto.LoginUserDto;
import com.khoabeo.quanlyphongkham.dto.RefreshTokenRequestDTO;
import com.khoabeo.quanlyphongkham.dto.RegisterUserDto;
import com.khoabeo.quanlyphongkham.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
        
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginUserDto loginUserDto) {
        JWTAuthResponse jwtAuthResponse = this.authService.login(loginUserDto);
        return ResponseEntity.ok(jwtAuthResponse);
    }



    @PostMapping("/refreshToken")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        JWTAuthResponse jwtAuthResponse = this.authService.refreshToken(refreshTokenRequestDTO);

        return ResponseEntity.ok()
                .body(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        String messages = this.authService.register(registerUserDto);
        return ResponseEntity.ok(messages);

    }

}