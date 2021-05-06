package com.vacinasja.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class LoginFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "funcionario_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Funcionario funcionario;

    public LoginFuncionario() {
    }

    public LoginFuncionario(Login login, Funcionario funcionario) {
        this.login = login;
        this.funcionario = funcionario;
    }

    public Login getLogin() {
        return login;
    }

    public String getStatusCadastro() {
        return this.funcionario.getStatusCadastroString();
    }
}
