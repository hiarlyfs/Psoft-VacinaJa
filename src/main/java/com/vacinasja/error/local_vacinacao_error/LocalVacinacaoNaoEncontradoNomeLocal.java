package com.vacinasja.error.local_vacinacao_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocalVacinacaoNaoEncontradoNomeLocal extends Exception {
	
	static final String LOCAL_VACINACAO_NAO_ENCONTRADO_NOME_LOCAL = "O local de vacinacao de nome %s não foi encontrado.";

	public LocalVacinacaoNaoEncontradoNomeLocal(String nomeLocal) {
		super(String.format(LOCAL_VACINACAO_NAO_ENCONTRADO_NOME_LOCAL, nomeLocal));
	}
	
}
