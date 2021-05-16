package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class CidadaoEstadoNaoAgendavel extends Exception {
	static final String CIDADAO_ESTADO_NAO_AGENDAVEL = "O cidadão de cartão sus %s não está em um estado disponível para agendamento.";
	
	public CidadaoEstadoNaoAgendavel(String cartaoSUS) {
		super(String.format(CIDADAO_ESTADO_NAO_AGENDAVEL, cartaoSUS));
	}
}
