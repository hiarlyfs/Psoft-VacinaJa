package com.vacinasja.dto.agendamento;

public class LocalVacinacaoDto {
	private Long id;
	private String nomeLocal;
	
	public LocalVacinacaoDto() {
	}

	public LocalVacinacaoDto(Long id, String nomeLocal) {
		this.id = id;
		this.nomeLocal = nomeLocal;
	}

	public Long getId() {
		return id;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}
	
	
	
	
}
