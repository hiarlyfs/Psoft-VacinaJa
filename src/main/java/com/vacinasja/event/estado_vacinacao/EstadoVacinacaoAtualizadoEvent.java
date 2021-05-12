package com.vacinasja.event.estado_vacinacao;

import org.springframework.context.ApplicationEvent;

public class EstadoVacinacaoAtualizadoEvent extends ApplicationEvent {

	public EstadoVacinacaoAtualizadoEvent(Object source) {
		super(source);
	}

}
