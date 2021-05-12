package com.vacinasja.service.funcionario_service;

import com.vacinasja.dto.funcionario.InsertFuncionarioDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontrado;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Funcionario;
import com.vacinasja.model.LoginFuncionario;

import java.util.Map;

public interface FuncionarioService {
    LoginFuncionario save(InsertFuncionarioDto insertFuncionarioDto) throws CidadaoNaoEncontrado, StatusInvalido, TipoLoginInvalido;

}
