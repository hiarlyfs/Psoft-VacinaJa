package com.vacinasja.dto.lote_vacina;

import org.springframework.format.annotation.DateTimeFormat;

public class InsertLoteVacinaDto {
	
    private Long vacina_id;
    
	private Integer qtdDoses;
	
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dataValidade;

	public InsertLoteVacinaDto(String dataValidade, Integer qtdDoses, Long vacina_id) {
		super();
		this.dataValidade = dataValidade;
		this.qtdDoses = qtdDoses;
		this.vacina_id = vacina_id;
	}
	
	public Long getVacinaId() {
		return vacina_id;
	}
	
	public Integer getQtdDoses() {
		return qtdDoses;
	}

	public String getDataValidade() {
		return dataValidade;
	}
	
}
