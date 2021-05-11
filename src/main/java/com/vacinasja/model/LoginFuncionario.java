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

    @OneToOne(cascade = CascadeType.ALL)
    private StatusCadastro statusCadastro;


    public LoginFuncionario() {
    }

    public LoginFuncionario(Login login, Funcionario funcionario, StatusCadastro statusCadastro) {
        this.login = login;
        this.funcionario = funcionario;
        this.statusCadastro = statusCadastro;
    }

    public Login getLogin() {
        return login;
    }

    public String getStatusCadastro() {
        return this.statusCadastro.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setStatusCadastro(StatusCadastro novoStatusCadastro) {
        this.statusCadastro = novoStatusCadastro;
    }
}
