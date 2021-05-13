package com.vacinasja.component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vacinasja.event.estado_vacinacao.EstadoVacinacaoAtualizadoEvent;
import com.vacinasja.event.passou_dia.PassouDiaEvent;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LocalVacinacao;
import com.vacinasja.repository.CidadaoRepository;
import com.vacinasja.repository.LocalVacinacaoRepository;
import com.vacinasja.service.agendamento_service.AgendamentoService;
import com.vacinasja.service.agendamento_service.LocalVacinacaoService;

@Component
public class PassouDiaListener implements ApplicationListener<PassouDiaEvent>{

	@Autowired
	CidadaoRepository cidadaoRepository;
	
	@Autowired
	LocalVacinacaoRepository localVacinacaoRepository;
	
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void onApplicationEvent(PassouDiaEvent event) {
		checarTodosCidadaos();
		checarTodosAgendamentos();
	}
	
	private void checarTodosAgendamentos() {
		System.out.println("Checando agendamentos para hoje.");
		List<LocalVacinacao> locais = localVacinacaoRepository.findAll();
		for (LocalVacinacao local:locais) {
			checaLocalVacinacao(local);
		}
	}
	
	private void checaLocalVacinacao(LocalVacinacao local) {
		local.removeAgendamentosAnteriores();
		localVacinacaoRepository.save(local);
	}

	private void checarTodosCidadaos() {
		System.out.println("Atualizando estado de vacinação por dia.");
		List<Cidadao> cidadaos = cidadaoRepository.findAll();
		
		ZoneId zid = ZoneId.of("America/Sao_Paulo");
		LocalDate hoje = LocalDate.now(zid);
		for (Cidadao cidadao: cidadaos) {
			checarCidadao(cidadao, hoje);
		}
	}
	
	private void checarCidadao(Cidadao cidadao, LocalDate hoje) {
		Boolean resposta = cidadao.passarEstagio(hoje);
		if (resposta) {
			cidadaoRepository.save(cidadao);
			applicationEventPublisher.publishEvent(new EstadoVacinacaoAtualizadoEvent(cidadao));
		}
	}

}
