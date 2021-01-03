package com.grzesiek.RedditClone.service;

import com.grzesiek.RedditClone.exceptions.SpringRedditException;
import com.grzesiek.RedditClone.model.RefreshToken;
import com.grzesiek.RedditClone.repository.RefreshTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepo;

    RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
//        The first method we have is generateRefreshToken() which creates a random 128 bit UUID String and we are using that as our token. We are setting the createdDate as Instant.now(). And then we are saving the token to our MySQL Database.

        return refreshTokenRepo.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }
    public void deleteRefreshToken(String token) {
        refreshTokenRepo.deleteByToken(token);
    }
}
