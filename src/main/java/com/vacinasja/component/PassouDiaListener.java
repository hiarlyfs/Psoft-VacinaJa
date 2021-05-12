package com.vacinasja.component;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vacinasja.event.estado_vacinacao.EstadoVacinacaoAtualizadoEvent;
import com.vacinasja.event.passou_dia.PassouDiaEvent;
import com.vacinasja.model.Cidadao;
import com.vacinasja.repository.CidadaoRepository;

@Component
public class PassouDiaListener implements ApplicationListener<PassouDiaEvent>{

	@Autowired
	CidadaoRepository cidadaoRepository;
	
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void onApplicationEvent(PassouDiaEvent event) {
		checarTodosCidadaos();
	}

	private void checarTodosCidadaos() {
		System.out.println("Atualizando estado de vacinação por dia.");
		List<Cidadao> cidadaos = cidadaoRepository.findAll();
		LocalDate hoje = LocalDate.now();
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
