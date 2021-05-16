package com.vacinasja.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vacinasja.dto.agendamento.AgendamentoDto;
import com.vacinasja.dto.agendamento.InsertAgendarDto;
import com.vacinasja.dto.agendamento.ListAgendamentoDto;
import com.vacinasja.error.agendamento_error.AgendamentoJaUtilizado;
import com.vacinasja.error.agendamento_error.CidadaoEstadoNaoAgendavel;
import com.vacinasja.error.agendamento_error.CidadaoJaAgendado;
import com.vacinasja.error.agendamento_error.CidadaoSemAgendamentos;
import com.vacinasja.error.agendamento_error.DataAgendamentoNaoAceita;
import com.vacinasja.error.agendamento_error.HorarioVacinacaoNaoEncontrado;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoId;
import com.vacinasja.model.HorarioVacinacao;
import com.vacinasja.service.agendamento_service.AgendamentoService;
import com.vacinasja.service.agendamento_service.LocalVacinacaoService;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin
public class AgendamentoController {
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@Autowired
	LocalVacinacaoService localVacinacaoService;
	
	@PreAuthorize("hasRole('ROLE_CIDADAO')")
	@PostMapping("")
	public ResponseEntity<AgendamentoDto> agendar(Authentication authentication, @RequestBody InsertAgendarDto agendamentoDto) throws HorarioVacinacaoNaoEncontrado, CidadaoNaoEncontradoCartaoSus, LocalVacinacaoNaoEncontradoId, ParseException, CidadaoEstadoNaoAgendavel, CidadaoJaAgendado, AgendamentoJaUtilizado, DataAgendamentoNaoAceita, CidadaoSemAgendamentos {
		AgendamentoDto novoAgendamento = agendamentoService.agendar(authentication.getName(), agendamentoDto);
		return new ResponseEntity<AgendamentoDto>(novoAgendamento, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_CIDADAO')")
	@GetMapping("/horarios")
	public ResponseEntity<List<HorarioVacinacao>> verHorarios(){
		List<HorarioVacinacao> horarios = localVacinacaoService.getAllHorarios();
		return new ResponseEntity<List<HorarioVacinacao>>(horarios, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CIDADAO')")
	@GetMapping("")
	public ResponseEntity<AgendamentoDto> verificarAgendamento(Authentication authentication) throws CidadaoNaoEncontradoCartaoSus, CidadaoSemAgendamentos {
		AgendamentoDto agendamentoEncontrado = agendamentoService.verAgendamentoCidadao(authentication.getName());
		return new ResponseEntity<AgendamentoDto>(agendamentoEncontrado, HttpStatus.OK);
	}
	
}
