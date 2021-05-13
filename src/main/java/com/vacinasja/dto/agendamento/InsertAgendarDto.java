package com.vacinasja.dto.agendamento;

import org.springframework.format.annotation.DateTimeFormat;

public class InsertAgendarDto {

	private Long localVacinacaoId;
	
	private Long horarioId;
	
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dataAgendamento;
    
    public InsertAgendarDto() {
	}

	public Long getLocalVacinacaoId() {
		return localVacinacaoId;
	}

	public Long getHorarioId() {
		return horarioId;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}
    
    
}
