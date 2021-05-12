package com.vacinasja.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vacinasja.error.template_error.TemplateNaoEncontrado;
import com.vacinasja.event.estado_vacinacao.*;
import com.vacinasja.model.Cidadao;
import com.vacinasja.service.email_service.EmailService;

@Component
public class EstadoVacinacaoListener implements ApplicationListener<EstadoVacinacaoAtualizadoEvent>{

	@Autowired
	EmailService emailService;
	
	@Override
	public void onApplicationEvent(EstadoVacinacaoAtualizadoEvent event){
		Cidadao cidadao = (Cidadao) event.getSource();
		enviarEmail(cidadao);
	}
	
	private void enviarEmail(Cidadao cidadao) {
		Map<String, String> values = new HashMap<String, String>();
		values.put("nomeUsuario", cidadao.getNome());
		values.put("mensagemEmail", cidadao.getEstagioVacinacao());
		try {
			emailService.enviarEmailComTemplate(cidadao.getEmail(), "Atualização Vacinação", "habilitado", values);
		} catch (MessagingException | IOException | TemplateNaoEncontrado e) {
			e.printStackTrace();
		}
	}

}
