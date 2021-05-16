package com.vacinasja.dto.cidadao_vacina;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;
import com.vacinasja.model.LoteVacina;
import com.vacinasja.model.Vacina;

public class CidadaoVacinacaoDto {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataVacinacao;
    
    
    private Long idLote;
    private Long idVacina;
    private int numDose;
    
    public CidadaoVacinacaoDto() {

    }

    public CidadaoVacinacaoDto(String cpf, Date dataVacinacao, Long idLote, Long idVacina, int numDose) {
        this.cpf = cpf;
        this.dataVacinacao = dataVacinacao;
        this.idLote = idLote;
        this.idVacina = idVacina;
        this.numDose = numDose;
    }

	public String getCpf() {
		return cpf;
	}

	public Date getDataVacinacao() {
		return dataVacinacao;
	}


	public Long getLote() {
		return idLote;
	}
	
	public Long getTipoVacina() {
		return idVacina;
	}


	public int getNumDose() {
		return numDose;
	}
    
}
