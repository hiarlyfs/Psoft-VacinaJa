package com.vacinasja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.vacinasja.error.agendamento_error.AgendamentoJaUtilizado;
import com.vacinasja.error.agendamento_error.CidadaoJaAgendado;
import com.vacinasja.error.agendamento_error.CidadaoSemAgendamentos;

@Entity
public class LocalVacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nomeLocal;
    
    @OneToMany(orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    private List<Agendamento> agendamentos;
    
    public LocalVacinacao() {
    	this.agendamentos = new ArrayList<Agendamento>();
	}

	public LocalVacinacao(String local) {
		this.nomeLocal = local;
		this.agendamentos = new ArrayList<Agendamento>();
	}

	public Long getId() {
		return id;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}
	
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	
	

	@Override
	public String toString() {
		return "Local de Vacinação " + id + " - " + nomeLocal;
	}

	public void removeAgendamentosAnteriores(LocalDate hoje) {
		Iterator<Agendamento> iterator = agendamentos.iterator();
		
		while (iterator.hasNext()) {
			Agendamento atual = iterator.next();
			if (atual.ehAntigo(hoje)) iterator.remove();
		}
	}
	
	public Agendamento getAgendamentoByCidadao(Cidadao cidadao) throws CidadaoSemAgendamentos {
		for (Agendamento agendamento: agendamentos) {
			if (agendamento.getCidadao().getCpf().equals(cidadao.getCpf())) {
				return agendamento;
			}
		}
		throw new CidadaoSemAgendamentos();
	}
	
	public void agendar(Cidadao cidadao, Date data, HorarioVacinacao horario) throws AgendamentoJaUtilizado, CidadaoJaAgendado {
		if (existeCidadaoAgendado(cidadao)) throw new CidadaoJaAgendado();
		if (horarioConflita(data, horario)) throw new AgendamentoJaUtilizado();
		agendamentos.add(new Agendamento(cidadao, data, horario));
	}
	
	public boolean existeCidadaoAgendado(Cidadao cidadao) {
		for (Agendamento agendamento: agendamentos) {
			if (agendamento.getCidadao().getCpf().equals(cidadao.getCpf())){
				return true;
			}
		}
		return false;
	}
	
	private boolean horarioConflita(Date data, HorarioVacinacao horario) {
		for (Agendamento agendamento: agendamentos) {
			if (compareData(data, agendamento.getData())) {
				if (agendamento.getHorario().getId() == horario.getId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean compareData(Date data1, Date data2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(data1);
		cal2.setTime(data2);
		boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
		                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		return sameDay;
	}
    
    
}
