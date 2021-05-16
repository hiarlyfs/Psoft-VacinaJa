package com.vacinasja.controller;

import com.vacinasja.dto.login.LoginDto;
import com.vacinasja.error.login_error.AguardandoAprovacao;
import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.service.login_service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws LoginInvalido, AguardandoAprovacao {
        String jwt = loginService.login(loginDto);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
