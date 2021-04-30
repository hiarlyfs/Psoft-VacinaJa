package com.vacinasja.service.token_authenticate_service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenAuthenticateService {
    String createToken(String login);
    Authentication getAuthentication(HttpServletRequest request);
}
