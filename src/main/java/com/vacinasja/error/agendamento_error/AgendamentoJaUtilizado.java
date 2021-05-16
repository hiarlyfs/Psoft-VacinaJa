package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AgendamentoJaUtilizado extends Exception {
	static final String AGENDAMENTO_JA_UTILIZADO = "O horário e dia já está agendado para outro cidadão.";
	
	public AgendamentoJaUtilizado() {
		super(AGENDAMENTO_JA_UTILIZADO);
	}
}
