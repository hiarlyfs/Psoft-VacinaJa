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

    public Funcionario() {
    }

    public String getCpf() {
        return this.cidadao.getCpf();
    }

    public Funcionario(Cidadao cidadao, String cargo, String localTrabalho) {
        this.cidadao = cidadao;
        this.cargo = cargo;
        this.localTrabalho = localTrabalho;
    }

}
