package com.vacinasja.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Cidadao cidadao;
    
	private Date data;
    
	@OneToOne
    private HorarioVacinacao horario;
    
    public Agendamento() {
	}
    
    public Agendamento(Cidadao cidadao, Date data, HorarioVacinacao horario) {
		super();
		this.cidadao = cidadao;
		this.data = data;
		this.horario = horario;
	}



	public Long getId() {
		return id;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public Date getData() {
		return data;
	}

	public HorarioVacinacao getHorario() {
		return horario;
	}
	
	public boolean ehAntigo(LocalDate hoje) {
		LocalDate dataAgendamento = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if (hoje.isAfter(dataAgendamento)) return true;
		return false;
	}
}
