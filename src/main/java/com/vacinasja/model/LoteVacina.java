package com.vacinasja.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.micrometer.core.lang.NonNull;

@Entity
public class LoteVacina {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@NonNull
    @Temporal(TemporalType.DATE)
    private Date dataValidade;

    @NonNull
	private Integer qtdDoses;
    
    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;
    
    public LoteVacina() {
	}

	public LoteVacina(Date dataValidade, Integer qtdDoses, Vacina vacina) {
		super();
		this.dataValidade = dataValidade;
		this.qtdDoses = qtdDoses;
		this.vacina = vacina;
	}
	
    public Date getDataValidade() {
		return dataValidade;
	}

	public Integer getQtdDoses() {
		return qtdDoses;
	}

	public Long getId() {
		return id;
	}

	public Vacina getTipoVacina() {
		return vacina;
	}
    
    
}
