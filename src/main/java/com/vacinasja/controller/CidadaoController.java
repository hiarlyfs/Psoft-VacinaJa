package com.vacinasja.controller;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.dto.cidadao_vacina.CidadaoVacinacaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.cidadao_error.CidadaoNaoHabilitado;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.error.verificacoes_error.CPFInvalido;
import com.vacinasja.error.verificacoes_error.EmailInvalido;
import com.vacinasja.error.verificacoes_error.TelefoneInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.service.cidadao_service.CidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/cidadao")
@CrossOrigin
public class CidadaoController {

    @Autowired
    CidadaoService cidadaoService;

    @PostMapping("")
    public ResponseEntity<LoginCidadao> save(@RequestBody InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido, EmailInvalido, TelefoneInvalido, CPFInvalido {
        LoginCidadao loginCidadao = cidadaoService.save(insertCidadaoDto);

        return new ResponseEntity<>(loginCidadao, HttpStatus.OK);
    }

    @GetMapping("/estagio")
    public ResponseEntity<String> listaEstagioCidadao(Authentication authentication) throws CidadaoNaoEncontradoCartaoSus {
        String estagio = cidadaoService.listaEstagioCidadao(authentication.getName());
        return new ResponseEntity<>(estagio, HttpStatus.OK);
    }    
    
    @PutMapping("")
    public ResponseEntity<Cidadao> atualizaCidadao(@RequestBody UpdateCidadaoDto updateCidadaoDto, Authentication authentication) throws CidadaoNaoEncontradoCartaoSus, EmailInvalido, TelefoneInvalido {
        Cidadao cidadao = cidadaoService.update(authentication.getName(), updateCidadaoDto);
        return new ResponseEntity<>(cidadao, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Cidadao> getCidadao(Authentication authentication) throws CidadaoNaoEncontradoCartaoSus {
        Cidadao cidadao = cidadaoService.findByCartaoSus(authentication.getName());
        return new ResponseEntity<>(cidadao, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @PostMapping("/registrar")
    public ResponseEntity<CidadaoVacinacao> registrarVacina(@RequestBody CidadaoVacinacaoDto cidadaoVacinacao) throws ParseException, LoteVacinaInexistente, VacinaInexistente, CidadaoNaoEncontradoCpf, CidadaoNaoHabilitado {
        CidadaoVacinacao registrarVacinacao = cidadaoService.registrarVacinacao(cidadaoVacinacao);
        return new ResponseEntity<>(registrarVacinacao, HttpStatus.OK);
    }
    
   @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
   @PostMapping("/habilitar-profissao")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoProfissao(@RequestBody String profissao){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.habilitarByProfissao(profissao);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
   }
   
   @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
   @PostMapping("/habilitar-idade")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoIdade(@RequestBody Integer idade){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.habilitarByIdade(idade);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
	}
   
   @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
   @PostMapping("/habilitar-comorbidade")
   public ResponseEntity<List<Cidadao>> habilitarCidadaoComorbidade(@RequestBody String comorbidade){
	   List<Cidadao> cidadaosHabilitados = cidadaoService.habilitarByComorbidade(comorbidade);
	   return new ResponseEntity<List<Cidadao>>(cidadaosHabilitados, HttpStatus.OK);
	}
   
}
