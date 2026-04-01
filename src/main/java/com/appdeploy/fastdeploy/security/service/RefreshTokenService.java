package com.appdeploy.fastdeploy.security.service;

import com.appdeploy.fastdeploy.security.dto.LoginResponse;
import com.appdeploy.fastdeploy.security.entity.RefreshToken;
import com.appdeploy.fastdeploy.security.entity.User;
import com.appdeploy.fastdeploy.security.repository.RefreshTokenRepository;
import com.appdeploy.fastdeploy.security.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthService authService;
    private final AuthUtil authUtil;

    @Transactional
    public String createToken(String username){

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUsername(username);
        refreshToken.setExpirationTime(new Date(System.currentTimeMillis()+1000*60*60*24));

        refreshToken = refreshTokenRepository.save(refreshToken);

        return  refreshToken.getToken();
    }

    public Boolean checkTokenExpiration(Date expirationDate){

        return new Date(System.currentTimeMillis()).compareTo(expirationDate)>0;
    }

    @Transactional
    public LoginResponse getNewTokens(String token){

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new IllegalArgumentException("Invalid token passed"));

        boolean isExpired = checkTokenExpiration(refreshToken.getExpirationTime());

        refreshTokenRepository.delete(refreshToken);

        if(isExpired){

            throw new IllegalStateException("Token expired");
        }

        User user = authService.fetchUserByUsername(refreshToken.getUsername());

         String jwtToken = authUtil.createAccessToken(user);

         String newRefreshToken = createToken(refreshToken.getUsername());

         return LoginResponse.builder()
                 .jwtToken(jwtToken)
                 .refreshToken(newRefreshToken)
                 .build();

    }

    public void deleteToken(String token){

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()->new IllegalArgumentException("invalid token"));

        refreshTokenRepository.delete(refreshToken);

    }


}
