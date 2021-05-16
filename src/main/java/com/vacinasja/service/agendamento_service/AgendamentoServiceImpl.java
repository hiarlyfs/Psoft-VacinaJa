package com.vacinasja.service.agendamento_service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.vacinasja.model.Agendamento;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.HorarioVacinacao;
import com.vacinasja.model.LocalVacinacao;
import com.vacinasja.repository.CidadaoRepository;
import com.vacinasja.repository.HorarioVacinacaoRepository;
import com.vacinasja.repository.LocalVacinacaoRepository;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {
	
	@Autowired
	LocalVacinacaoRepository localVacinacaoRepository;
	
	@Autowired
	HorarioVacinacaoRepository horarioVacinacaoRepository;
	
	@Autowired
	CidadaoRepository cidadaoRepository;
	
	@Override
	public AgendamentoDto verAgendamentoCidadao(String cartaoSUS) throws CidadaoNaoEncontradoCartaoSus, CidadaoSemAgendamentos {
		Optional<Cidadao> optionalCidadao = cidadaoRepository.findByCartaoSus(cartaoSUS);
		if(!optionalCidadao.isPresent()) throw new CidadaoNaoEncontradoCartaoSus(cartaoSUS);
		return getAgendamentoByCidadao(optionalCidadao.get());
	}
	
	@Override
	public AgendamentoDto agendar(String cartaoSUS, InsertAgendarDto agendarDto) throws HorarioVacinacaoNaoEncontrado, CidadaoNaoEncontradoCartaoSus, LocalVacinacaoNaoEncontradoId, ParseException, CidadaoEstadoNaoAgendavel, CidadaoJaAgendado, AgendamentoJaUtilizado, DataAgendamentoNaoAceita, CidadaoSemAgendamentos {
		Date dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(agendarDto.getDataAgendamento());
		
		Optional<HorarioVacinacao> optionalHorario = horarioVacinacaoRepository.findById(agendarDto.getHorarioId());
		if (!optionalHorario.isPresent()) throw new HorarioVacinacaoNaoEncontrado(agendarDto.getHorarioId());
		
		verificaDataAgendamento(dataAgendamento, optionalHorario.get());
		
		Optional<LocalVacinacao> optionalLocalVacinacao = localVacinacaoRepository.findById(agendarDto.getLocalVacinacaoId());
		if(!optionalLocalVacinacao.isPresent()) throw new LocalVacinacaoNaoEncontradoId(agendarDto.getLocalVacinacaoId());
		
		Optional<Cidadao> optionalCidadao = cidadaoRepository.findByCartaoSus(cartaoSUS);
		if(!optionalCidadao.isPresent()) throw new CidadaoNaoEncontradoCartaoSus(cartaoSUS);
		
		Cidadao cidadao = optionalCidadao.get();
		verificaCidadaoEstado(cidadao);
		verificaCidadaoJaAgendado(cidadao);
		
		LocalVacinacao localVacinacao = optionalLocalVacinacao.get();
		localVacinacao.agendar(cidadao, dataAgendamento, optionalHorario.get());
		localVacinacaoRepository.save(localVacinacao);
		
		return getAgendamentoByCidadao(cidadao);
	}
	
	private void verificaDataAgendamento(Date data, HorarioVacinacao horarioVacinacao) throws DataAgendamentoNaoAceita {
		LocalDate dataAgendamento = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ZoneId zid = ZoneId.of("America/Sao_Paulo");
		LocalDate hoje = LocalDate.now(zid);
		
		if (hoje.isAfter(dataAgendamento)) throw new DataAgendamentoNaoAceita();
		if (!hoje.isBefore(dataAgendamento) && horarioVacinacao.ehAntes(data)) throw new DataAgendamentoNaoAceita();
	}
	
	private void verificaCidadaoEstado(Cidadao cidadao) throws CidadaoEstadoNaoAgendavel {
		if (cidadao.pegaIntEstado() != 2 && cidadao.pegaIntEstado() != 4) throw new CidadaoEstadoNaoAgendavel(cidadao.getCartaoSus());
	}
	
	private void verificaCidadaoJaAgendado(Cidadao cidadao) throws CidadaoJaAgendado {
		List<LocalVacinacao> listaLocaisVacinacao = localVacinacaoRepository.findAll();
		for (LocalVacinacao localVacinacao : listaLocaisVacinacao) {
			if (localVacinacao.existeCidadaoAgendado(cidadao)) {
				throw new CidadaoJaAgendado();
			}
		}
	}
	
	private AgendamentoDto getAgendamentoByCidadao(Cidadao cidadao) throws CidadaoSemAgendamentos {
		List<LocalVacinacao> listaLocaisVacinacao = localVacinacaoRepository.findAll();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		for (LocalVacinacao localVacinacao : listaLocaisVacinacao) {
			if (localVacinacao.existeCidadaoAgendado(cidadao)) {
				Agendamento tempAgendamento = localVacinacao.getAgendamentoByCidadao(cidadao);
				return new AgendamentoDto(dateFormat.format(tempAgendamento.getData()), tempAgendamento.getHorario().getHora(), localVacinacao.toString());
			}
		}
		throw new CidadaoSemAgendamentos();
	}
}
