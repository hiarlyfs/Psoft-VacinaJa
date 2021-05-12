package com.vacinasja.controller;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontrado;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.service.cidadao_service.CidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/cidadao")
@CrossOrigin
public class CidadaoController {

    @Autowired
    CidadaoService cidadaoService;

    @PostMapping("")
    public ResponseEntity<LoginCidadao> save(@RequestBody InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido {
        LoginCidadao loginCidadao = cidadaoService.save(insertCidadaoDto);

        return new ResponseEntity<>(loginCidadao, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> listaEstagioCidadao(@RequestParam String cpf) throws CidadaoNaoEncontrado {
        // atualizar para pegar informação de usuário logado da sessão
        String estagio = cidadaoService.listaEstagioCidadao(cpf);
        return new ResponseEntity<>(estagio, HttpStatus.OK);
    }
}
