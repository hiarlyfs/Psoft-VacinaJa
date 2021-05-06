package com.vacinasja.dto.funcionario;

public class InsertFuncionarioDto {

    private String cpf;
    private String cargo;
    private String localTrabalho;

    public InsertFuncionarioDto() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }
}
