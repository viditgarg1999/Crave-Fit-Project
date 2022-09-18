package com.leggo.fit.dto.login;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class RefreshTokenRequestModel {
    private String refreshToken;

    public RefreshTokenRequestModel(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequestModel() {
    }
}
