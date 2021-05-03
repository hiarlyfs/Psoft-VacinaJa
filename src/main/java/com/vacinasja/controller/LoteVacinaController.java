package com.vacinasja.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vacinasja.dto.lote_vacina.InsertLoteVacinaDto;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.LoteVacina;
import com.vacinasja.service.lote_vacina_service.LoteVacinaService;

@RestController
@RequestMapping("/lotevacina")
@CrossOrigin
public class LoteVacinaController {
	
	@Autowired
	LoteVacinaService loteVacinaService;
	
    @PostMapping("")
    public ResponseEntity<LoteVacina> criarLoteVacina(@RequestBody InsertLoteVacinaDto loteVacinaDto) throws ParseException, VacinaInexistente {
        LoteVacina novoLoteVacina = loteVacinaService.cadastrarLoteVacina(loteVacinaDto);
        return new ResponseEntity<LoteVacina>(novoLoteVacina, HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public ResponseEntity<LoteVacina> getLoteVacinaById(@RequestParam Long id) throws LoteVacinaInexistente{
		LoteVacina vacinaEncontrada = loteVacinaService.getLoteVacinaById(id);
		return new ResponseEntity<LoteVacina>(vacinaEncontrada, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<LoteVacina>> getAllLoteVacina() {
    	List<LoteVacina> listaTodosLoteVacina = loteVacinaService.allLotesVacina();
    	return new ResponseEntity<List<LoteVacina>>(listaTodosLoteVacina, HttpStatus.OK);
    }
}
