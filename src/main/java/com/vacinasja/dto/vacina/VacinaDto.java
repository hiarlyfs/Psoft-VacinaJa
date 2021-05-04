package com.vacinasja.dto.vacina;

public class VacinaDto {
	private String fabricante;
	
	private Integer dosesNecessarias;

	private Integer intervaloDoses;

	public VacinaDto(String fabricante, Integer dosesNecessarias, Integer intervaloDoses) {
		super();
		this.fabricante = fabricante;
		this.dosesNecessarias = dosesNecessarias;
		this.intervaloDoses = intervaloDoses;
	}

	public String getFabricante() {
		return fabricante;
	}

	public Integer getDosesNecessarias() {
		return dosesNecessarias;
	}

	public Integer getIntervaloDoses() {
		return intervaloDoses;
	}
	
}
