package com.vacinasja.dto.agendamento;

public class ListAgendamentoDto {
	private String data;
	private String horario;
	private String cartaoSUS;
	
	public ListAgendamentoDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ListAgendamentoDto(String data, String horario, String cartaoSUS) {
		super();
		this.data = data;
		this.horario = horario;
		this.cartaoSUS = cartaoSUS;
	}

	public String getData() {
		return data;
	}
	public String getHorario() {
		return horario;
	}
	public String getCartaoSUS() {
		return cartaoSUS;
	}
}
