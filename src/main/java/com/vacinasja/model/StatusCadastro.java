package com.vacinasja.model;

import javax.persistence.*;

@Entity
public class StatusCadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String status;

    public StatusCadastro() {
    }

    public String getStatus() {
        return this.status;
    }
}
