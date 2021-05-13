package com.vacinasja.service.agendamento_service;

import java.util.List;

import com.vacinasja.dto.agendamento.InsertLocalVacinacaoDto;
import com.vacinasja.dto.agendamento.ListAgendamentoDto;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoId;
import com.vacinasja.model.HorarioVacinacao;
import com.vacinasja.model.LocalVacinacao;

public interface LocalVacinacaoService {
	LocalVacinacao save(InsertLocalVacinacaoDto localVacinacaoDto);
	
	List<LocalVacinacao> findAll();

	List<HorarioVacinacao> getAllHorarios();

	List<ListAgendamentoDto> listarAgendamentosLocal(Long localVacinacaoId) throws LocalVacinacaoNaoEncontradoId;
}
