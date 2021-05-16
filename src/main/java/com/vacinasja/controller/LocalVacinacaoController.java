package com.vacinasja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vacinasja.dto.agendamento.InsertLocalVacinacaoDto;
import com.vacinasja.dto.agendamento.ListAgendamentoDto;
import com.vacinasja.dto.agendamento.LocalVacinacaoDto;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoId;
import com.vacinasja.model.LocalVacinacao;
import com.vacinasja.service.agendamento_service.AgendamentoService;
import com.vacinasja.service.agendamento_service.LocalVacinacaoService;

@RestController
@RequestMapping("/localvacinacao")
@CrossOrigin
public class LocalVacinacaoController {

	@Autowired
	LocalVacinacaoService localVacinacaoService;
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("")
	public ResponseEntity<LocalVacinacao> criarLocalVacinacao(@RequestBody InsertLocalVacinacaoDto localVacinacaoDto) {
		LocalVacinacao novoLocal = localVacinacaoService.save(localVacinacaoDto);
		return new ResponseEntity<LocalVacinacao>(novoLocal, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CIDADAO')") 
	@GetMapping("/all")
	public ResponseEntity<List<LocalVacinacaoDto>> getAllLocais() {
		List<LocalVacinacaoDto> locais = localVacinacaoService.findAll();
		return new ResponseEntity<List<LocalVacinacaoDto>>(locais, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_FUNCIONARIO')")
	@GetMapping("")
	public ResponseEntity<List<ListAgendamentoDto>> getAgendamentosLocal(@RequestParam Long localVacinacaoId) throws LocalVacinacaoNaoEncontradoId {
		List<ListAgendamentoDto> lista = localVacinacaoService.listarAgendamentosLocal(localVacinacaoId);
		return new ResponseEntity<List<ListAgendamentoDto>>(lista, HttpStatus.OK);
	}
	
}
