package com.vacinasja.controller;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.model.statecidadao.NaoHabilitado;
import com.vacinasja.model.statecidadao.Tomou1Dose;
import com.vacinasja.service.cidadao_service.CidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    @PostMapping("/registrar")
    public ResponseEntity<CidadaoVacinacao> registrarVacina(@RequestBody CidadaoVacinacao cidadaoVacinacao) throws ParseException, TipoLoginInvalido {
        CidadaoVacinacao registrarVacinacao = cidadaoService.registrarVacinacao(cidadaoVacinacao);
        return new ResponseEntity<>(registrarVacinacao, HttpStatus.OK);
    }
    
   
   @PostMapping("/habilitar-profissao")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoProfissao(@RequestBody String profissao){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.findByProfissao(profissao);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
   }
   
   /*@PostMapping("/habilitar")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoIdade(@RequestBody Integer idade){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.findByIdade(idade);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
	}*/
   
   @PostMapping("/habilitar-comorbidade")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoComorbidade(@RequestBody String comorbidade){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.findByComorbidade(comorbidade);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
	}
   
	
   
	   

    
}
