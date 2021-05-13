package com.vacinasja.controller;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.service.cidadao_service.CidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cidadao")
@CrossOrigin
public class CidadaoController {

    @Autowired
    CidadaoService cidadaoService;

    @PostMapping("/")
    public ResponseEntity<LoginCidadao> save(@RequestBody InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido {
        LoginCidadao loginCidadao = cidadaoService.save(insertCidadaoDto);

        return new ResponseEntity<>(loginCidadao, HttpStatus.OK);
    }
    
    
  /*  
    @PostMapping 
    public ResponseEntity<List<Cidadao>> habilitarVacinacaoIdade(@RequestBody  Date idadeMinima){
    	List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
    	
    	
    	
    	return new ResponseEntity<>(cidadaosHabilitados, HttpStatus.OK);
    }
    
    @PostMapping 
    public ResponseEntity<List<Cidadao>> habilitarVacinacaoIdadeComorbidade(@RequestBody  String comorbidade){
    	List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
    	
    	
    	
    	return new ResponseEntity<>(cidadaosHabilitados, HttpStatus.OK);
    }
    
    
    @PostMapping 
    public ResponseEntity<List<Cidadao>> habilitarVacinacaoProfissao(@RequestBody  String profissao){
    	List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
    	
    	
    	
    	return new ResponseEntity<>(cidadaosHabilitados, HttpStatus.OK);
    }
*/
    
    
}
