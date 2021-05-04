package com.vacinasja.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
