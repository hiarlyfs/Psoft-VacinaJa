package com.vacinasja.service.agendamento_service;

import java.text.ParseException;

import com.vacinasja.dto.agendamento.AgendamentoDto;
import com.vacinasja.dto.agendamento.InsertAgendarDto;
import com.vacinasja.error.agendamento_error.AgendamentoJaUtilizado;
import com.vacinasja.error.agendamento_error.CidadaoEstadoNaoAgendavel;
import com.vacinasja.error.agendamento_error.CidadaoJaAgendado;
import com.vacinasja.error.agendamento_error.CidadaoSemAgendamentos;
import com.vacinasja.error.agendamento_error.DataAgendamentoNaoAceita;
import com.vacinasja.error.agendamento_error.HorarioVacinacaoNaoEncontrado;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoId;

public interface AgendamentoService {
	AgendamentoDto agendar(String cartaoSUS, InsertAgendarDto agendarDto) throws HorarioVacinacaoNaoEncontrado,
	CidadaoNaoEncontradoCartaoSus, LocalVacinacaoNaoEncontradoId, ParseException, CidadaoEstadoNaoAgendavel, CidadaoJaAgendado, AgendamentoJaUtilizado, DataAgendamentoNaoAceita, CidadaoSemAgendamentos;

	AgendamentoDto verAgendamentoCidadao(String cartaoSUS) throws CidadaoNaoEncontradoCartaoSus, CidadaoSemAgendamentos;
}
