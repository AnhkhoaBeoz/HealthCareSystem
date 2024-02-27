package com.khoabeo.quanlyphongkham.service.Impl;


import com.khoabeo.quanlyphongkham.Constants.AppConstants;
import com.khoabeo.quanlyphongkham.entity.RefreshToken;
import com.khoabeo.quanlyphongkham.repository.AccountRepository;
import com.khoabeo.quanlyphongkham.repository.RefreshTokenRepository;
import com.khoabeo.quanlyphongkham.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    AccountRepository accountRepository;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, AccountRepository accountRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountRepository = accountRepository;
    }

    // Deletes a refresh token
    // Xóa một token làm mới
    @Override
    public void deteleTokenByRefreshToken(RefreshToken token) {
        this.refreshTokenRepository.delete(token);
        return;
    }

    // Finds a refresh token by username
    // Tìm kiếm token làm mới bằng tên người dùng
    @Override
    public RefreshToken findRefreshTokenByUserName(String username) {
        return this.refreshTokenRepository.findRefreshTokenByUserName(username);
    }

    // Creates a new refresh token
    // Tạo một token làm mới mới
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(AppConstants.JWT_REFRESH_TOKEN_EXPIRY)); /////Hết hạn 10 phút
        refreshToken.setAccount(this.accountRepository.findAccountByUserName(username).get());
        return refreshTokenRepository.save(refreshToken);
    }

    // Finds a refresh token by its token value
    // Tìm kiếm token làm mới bằng giá trị token
    public Optional<RefreshToken> findRefreshTokenByToken(String token) {
        return refreshTokenRepository.findRefreshTokenByToken(token);
    }

    // Verifies the expiration of a refresh token and deletes it if expired
    // Xác nhận xem một token làm mới đã hết hạn chưa và xóa nó nếu đã hết hạn
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}

