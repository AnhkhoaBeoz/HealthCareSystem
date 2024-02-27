package com.khoabeo.quanlyphongkham.service;


import com.khoabeo.quanlyphongkham.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String username);

    Optional<RefreshToken> findRefreshTokenByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshToken findRefreshTokenByUserName(String username);

    void deteleTokenByRefreshToken(RefreshToken token);


}
