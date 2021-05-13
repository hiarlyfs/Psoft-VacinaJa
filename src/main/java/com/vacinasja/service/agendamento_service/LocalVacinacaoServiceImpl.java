package com.vacinasja.service.agendamento_service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinasja.dto.agendamento.InsertLocalVacinacaoDto;
import com.vacinasja.dto.agendamento.ListAgendamentoDto;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoId;
import com.vacinasja.error.local_vacinacao_error.LocalVacinacaoNaoEncontradoNomeLocal;
import com.vacinasja.model.Agendamento;
import com.vacinasja.model.HorarioVacinacao;
import com.vacinasja.model.LocalVacinacao;
import com.vacinasja.repository.HorarioVacinacaoRepository;
import com.vacinasja.repository.LocalVacinacaoRepository;

@Service
public class LocalVacinacaoServiceImpl implements LocalVacinacaoService {

	@Autowired
	LocalVacinacaoRepository localVacinacaoRepository;
	
	@Autowired
	HorarioVacinacaoRepository horarioVacinacaoService;
	
	@Override
	public LocalVacinacao save(InsertLocalVacinacaoDto localVacinacaoDto) {
		LocalVacinacao novoLocal = new LocalVacinacao(localVacinacaoDto.getLocal());
		return localVacinacaoRepository.save(novoLocal);
	}

	@Override
	public List<ListAgendamentoDto> listarAgendamentosLocal(Long localVacinacaoId) throws LocalVacinacaoNaoEncontradoId {
		Optional<LocalVacinacao> optionalLocalVacinacao = localVacinacaoRepository.findById(localVacinacaoId);
		if(!optionalLocalVacinacao.isPresent()) throw new LocalVacinacaoNaoEncontradoId(localVacinacaoId);
		
		List<Agendamento> agendamentos = optionalLocalVacinacao.get().getAgendamentos();
		List<ListAgendamentoDto> agendamentosDto = new ArrayList<ListAgendamentoDto>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		for (Agendamento agendamento:agendamentos) {
			agendamentosDto.add(new ListAgendamentoDto(dateFormat.format(agendamento.getData()), agendamento.getHorario().getHora(), agendamento.getCidadao().getCartaoSus()));
		}
		return agendamentosDto;
	}

	@Override
	public List<LocalVacinacao> findAll() {
		List<LocalVacinacao> listaLocaisVacinacao = localVacinacaoRepository.findAll();
		return listaLocaisVacinacao;
	}
	
	@Override
	public List<HorarioVacinacao> getAllHorarios(){
		return horarioVacinacaoService.findAll();
	}

}
