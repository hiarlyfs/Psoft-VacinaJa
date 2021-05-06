package com.vacinasja.model;

import javax.persistence.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Cidadao cidadao;

    private String cargo;
    private String localTrabalho;

    @OneToOne(cascade = CascadeType.ALL)
    private StatusCadastro statusCadastro;

    public Funcionario() {
    }

    public String getCpf() {
        return this.cidadao.getCpf();
    }

    public Funcionario(Cidadao cidadao, String cargo, String localTrabalho, StatusCadastro statusCadastro) {
        this.cidadao = cidadao;
        this.cargo = cargo;
        this.localTrabalho = localTrabalho;
        this.statusCadastro = statusCadastro;
    }

    public String getStatusCadastroString() {
        return this.statusCadastro.getStatus();
    }
}
