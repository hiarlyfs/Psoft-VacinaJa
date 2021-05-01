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



}
