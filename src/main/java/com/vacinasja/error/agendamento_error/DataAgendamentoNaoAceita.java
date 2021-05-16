package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class DataAgendamentoNaoAceita extends Exception {
	
	static final String DATA_AGENDAMENTO_NAO_ACEITA = "Não é possível agendar para um dia ou hora anterior ao atual.";
	
	public DataAgendamentoNaoAceita() {
		super(DATA_AGENDAMENTO_NAO_ACEITA);
	}
}
