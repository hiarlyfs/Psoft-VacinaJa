package com.vacinasja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HorarioVacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String hora;
    
    public HorarioVacinacao() {
	}

	public HorarioVacinacao(String hora) {
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}

	public String getHora() {
		return hora;
	}
    
    
}
