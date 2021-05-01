package com.vacinasja.dto.cidadao;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class InsertCidadaoDto {

    private String nomeCompleto;
    private String endereco;
    private String cpf;
    private String cartaoSus;
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dataNascimento;

    private String telefone;
    private String profissao;
    private List<String> cormobidades;


    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getProfissao() {
        return profissao;
    }

    public List<String> getCormobidades() {
        return cormobidades;
    }
}
