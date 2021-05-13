package com.vacinasja.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

@Entity
public class CidadaoVacinacao {

		@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;

	    @NotNull
	    @Column(unique = true)
	    private String cpf;

	    @Temporal(TemporalType.DATE)
	    private Date dataVacinacao;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private LoteVacina lote;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private Vacina tipoVacina;
	    private int numDose;
	    
	    public CidadaoVacinacao() {

	    }

	    public CidadaoVacinacao(String cpf, Date dataVacinacao, LoteVacina lote, Vacina tipoVacina, int numDose) {
	        this.cpf = cpf;
	        this.dataVacinacao = dataVacinacao;
	        this.lote = lote;
	        this.tipoVacina = tipoVacina;
	        this.numDose = numDose;
	    }

		public String getCpf() {
			return cpf;
		}

		public Date getDataVacinacao() {
			return dataVacinacao;
		}


		public LoteVacina getLote() {
			return lote;
		}
		
		public Vacina getTipoVacina() {
			return tipoVacina;
		}


		public int getNumDose() {
			return numDose;
		}

	    
	    
	    
	    

	    
}
