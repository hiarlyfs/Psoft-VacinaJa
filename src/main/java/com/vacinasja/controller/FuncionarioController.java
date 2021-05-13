package com.vacinasja.controller;

import com.vacinasja.dto.funcionario.InsertFuncionarioDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.LoginFuncionario;
import com.vacinasja.service.funcionario_service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;


    @PostMapping("")
    public ResponseEntity<LoginFuncionario> save(@RequestBody InsertFuncionarioDto insertFuncionarioDto) throws TipoLoginInvalido, StatusInvalido, CidadaoNaoEncontradoCpf {
        LoginFuncionario loginFuncionario = funcionarioService.save(insertFuncionarioDto);

        return new ResponseEntity<>(loginFuncionario, HttpStatus.OK);
    }

}
