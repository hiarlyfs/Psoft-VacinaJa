package com.vacinasja.error.agendamento_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HorarioVacinacaoNaoEncontrado extends Exception {
	static final String HORARIO_VACINACAO_NAO_ENCONTRADO = "O horário de vacinação de id %s não foi encontrado.";
	public HorarioVacinacaoNaoEncontrado(Long horarioId) {
		super(String.format(HORARIO_VACINACAO_NAO_ENCONTRADO, horarioId));
	}
}
