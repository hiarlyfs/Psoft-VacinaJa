package com.vacinasja.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class LoginCidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadao_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cidadao cidadao;

    public LoginCidadao(String login, String password, Cidadao cidadao) {
        this.login = login;
        this.password = password;
        this.cidadao = cidadao;
    }

    public LoginCidadao() {}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
