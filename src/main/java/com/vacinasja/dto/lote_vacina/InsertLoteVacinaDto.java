package com.vacinasja.dto.lote_vacina;

import org.springframework.format.annotation.DateTimeFormat;

public class InsertLoteVacinaDto {
	
    private Long vacinaId;
    
	private Integer qtdDoses;
	
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dataValidade;

	public InsertLoteVacinaDto(String dataValidade, Integer qtdDoses, Long vacinaId) {
		super();
		this.dataValidade = dataValidade;
		this.qtdDoses = qtdDoses;
		this.vacinaId = vacinaId;
	}
	
	public Long getVacinaId() {
		return vacinaId;
	}
	
	public Integer getQtdDoses() {
		return qtdDoses;
	}

	public String getDataValidade() {
		return dataValidade;
	}
	
}
