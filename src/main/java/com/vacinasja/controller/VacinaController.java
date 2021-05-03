package com.vacinasja.controller;

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

import com.vacinasja.dto.vacina.VacinaDto;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.Vacina;
import com.vacinasja.service.vacina_service.VacinaService;

@RestController
@RequestMapping("/vacina")
@CrossOrigin
public class VacinaController {
	
	@Autowired
	VacinaService vacinaService;
	
    @PostMapping("")
    public ResponseEntity<Vacina> criarVacina(@RequestBody VacinaDto vacinaDto) {
        Vacina novaVacina = vacinaService.criarTipoVacina(vacinaDto);
        return new ResponseEntity<Vacina>(novaVacina, HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public ResponseEntity<Vacina> getVacinaById(@RequestParam Long id) throws VacinaInexistente{
		Vacina vacinaEncontrada = vacinaService.getVacinaById(id);
		return new ResponseEntity<Vacina>(vacinaEncontrada, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vacina>> getAllVacinas() {
    	List<Vacina> listaTodasVacinas = vacinaService.allTiposVacina();
    	return new ResponseEntity<List<Vacina>>(listaTodasVacinas, HttpStatus.OK);
    }

}
