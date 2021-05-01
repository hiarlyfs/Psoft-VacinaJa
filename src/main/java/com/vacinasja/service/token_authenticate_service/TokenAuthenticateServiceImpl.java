package com.vacinasja.service.token_authenticate_service;

import com.vacinasja.model.Login;
import com.vacinasja.service.login_service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TokenAuthenticateServiceImpl implements TokenAuthenticateService {

    @Autowired
    LoginService loginService;

    static final long EXPIRATION_TIME = 86_400_000;
    static final String SECRET = "16bd8dfba2191761b65d5a795806e530";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Override
    public String createToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            String login = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();


            if (login != null) {
                Login userLogin = loginService.findByUserLogin(login);

                if (userLogin != null) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + userLogin.getTipoLogin()));
                    return new UsernamePasswordAuthenticationToken(userLogin.getLogin(), "", authorities);
                }
            }

        }
        return null;
    }
}
