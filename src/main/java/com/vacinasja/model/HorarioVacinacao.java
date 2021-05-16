package com.vacinasja.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
	
	public boolean ehAntes(Date data1) {
		String[] entrada = hora.split(":");
		int hora1 = Integer.parseInt(entrada[0]);
		int min1 = Integer.parseInt(entrada[1]);

		ZoneId zid = ZoneId.of("America/Sao_Paulo");
		LocalDateTime hojeLocal = LocalDateTime.now(zid);
		
		int hora2 = hojeLocal.getHour();
		int min2 = hojeLocal.getMinute();
		
		int total1 = min1 + hora1*60;
		int total2 = min2 + hora2*60;
		return total2 > total1;
	}
    
    
}
