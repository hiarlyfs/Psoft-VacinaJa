package com.vacinasja.service.funcionario_service;

import com.vacinasja.dto.funcionario.InsertFuncionarioDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.LoginFuncionario;

public interface FuncionarioService {
    LoginFuncionario save(InsertFuncionarioDto insertFuncionarioDto) throws CidadaoNaoEncontradoCpf, StatusInvalido, TipoLoginInvalido;

}
