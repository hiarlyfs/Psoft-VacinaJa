package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CidadaoSemAgendamentos extends Exception {
	
	static final String CIDADAO_SEM_AGENDAMENTOS = "Não há agendamentos para esse cidadão.";
	
	public CidadaoSemAgendamentos() {
		super(CIDADAO_SEM_AGENDAMENTOS);
	}
}
