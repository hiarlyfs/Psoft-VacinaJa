package com.vacinasja.controller;

import com.vacinasja.dto.login_funcionario.AuthorizeFuncionarioDto;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.service.login_funcionario_service.LoginFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login-funcionario")
@CrossOrigin
public class LoginFuncionarioController {

    @Autowired
    LoginFuncionarioService loginFuncionarioService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/authorize")
    public ResponseEntity<List<Map<String, Object>>> getFuncionariosToAuthorize() throws StatusInvalido {
        List<Map<String, Object>> loginFuncionarios = loginFuncionarioService.getLoginsToAuthorize();
        return new ResponseEntity<>(loginFuncionarios, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/authorize")
    public ResponseEntity<List<Map<String, Object>>> authorizeFuncionarios(@RequestBody List<AuthorizeFuncionarioDto>  authorizeFuncionariosDto) throws StatusInvalido {
        loginFuncionarioService.authorizeFuncionarios(authorizeFuncionariosDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
