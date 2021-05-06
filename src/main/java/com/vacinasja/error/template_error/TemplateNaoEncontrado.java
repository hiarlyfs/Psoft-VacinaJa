package com.vacinasja.error.template_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class TemplateNaoEncontrado extends Exception{
    static final String TEMPLATE_NAO_ENCONTRADO = "O template de nome %s não foi encontrado nos arquivos do servidor.";

    public TemplateNaoEncontrado(String nomeTemplate) {
        super(String.format(TEMPLATE_NAO_ENCONTRADO, nomeTemplate));
    }
}
