package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CidadaoJaAgendado extends Exception {
	static final String CIDADAO_JA_AGENDADO = "O cidadão já está agendado.";
	
	public CidadaoJaAgendado() {
		super(CIDADAO_JA_AGENDADO);
	}
}
