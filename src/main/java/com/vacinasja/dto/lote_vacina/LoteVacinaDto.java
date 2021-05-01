package com.vacinasja.dto.lote_vacina;

import org.springframework.format.annotation.DateTimeFormat;

public class LoteVacinaDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dataValidade;

	private Integer qtdDoses;
    
    private Long vacina_id;

	public LoteVacinaDto(String dataValidade, Integer qtdDoses, Long vacina_id) {
		super();
		this.dataValidade = dataValidade;
		this.qtdDoses = qtdDoses;
		this.vacina_id = vacina_id;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public Integer getQtdDoses() {
		return qtdDoses;
	}

	public Long getVacinaId() {
		return vacina_id;
	}
}
