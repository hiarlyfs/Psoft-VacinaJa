package com.vacinasja.error.local_vacinacao_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocalVacinacaoNaoEncontradoId extends Exception {
	
	static final String LOCAL_VACINACAO_NAO_ENCONTRADO_ID = "O local de vacinacao de id %s não foi encontrado.";

	public LocalVacinacaoNaoEncontradoId(Long id) {
		super(String.format(LOCAL_VACINACAO_NAO_ENCONTRADO_ID, id));
	}
	
}
