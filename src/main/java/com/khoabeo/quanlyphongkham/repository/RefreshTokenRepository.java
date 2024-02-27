package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String token);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.account.userName = :username")
    RefreshToken findRefreshTokenByUserName(String username);


}