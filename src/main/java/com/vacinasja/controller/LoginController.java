package com.vacinasja.controller;

import com.vacinasja.dto.login.LoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    // EXPIRATION_TIME = 1 dia
    static final long EXPIRATION_TIME = 86_400_000;
    static final String SECRET = "16bd8dfba2191761b65d5a795806e530";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String JWT = Jwts.builder()
                .setSubject(loginDto.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return new ResponseEntity<>(JWT, HttpStatus.OK);
    }



}
