package com.vacinasja.controller;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.service.cidadao_service.CidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/estagio")
    public ResponseEntity<String> listaEstagioCidadao(Authentication authentication) throws CidadaoNaoEncontradoCartaoSus {
        String estagio = cidadaoService.listaEstagioCidadao(authentication.getName());
        return new ResponseEntity<>(estagio, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Cidadao> atualizaCidadao(@RequestBody UpdateCidadaoDto updateCidadaoDto, Authentication authentication) throws CidadaoNaoEncontradoCartaoSus {
        Cidadao cidadao = cidadaoService.update(authentication.getName(), updateCidadaoDto);
        return new ResponseEntity<>(cidadao, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Cidadao> getCidadao(Authentication authentication) throws CidadaoNaoEncontradoCartaoSus {
        Cidadao cidadao = cidadaoService.findByCartaoSus(authentication.getName());
        return new ResponseEntity<>(cidadao, HttpStatus.OK);
    }
}
