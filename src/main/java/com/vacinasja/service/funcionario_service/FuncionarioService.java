package com.vacinasja.service.funcionario_service;

import com.vacinasja.dto.funcionario.InsertFuncionarioDto;
import com.vacinasja.error.cidadao_error.CidadaoInvalido;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Funcionario;
import com.vacinasja.model.LoginFuncionario;

public interface FuncionarioService {
    LoginFuncionario save(InsertFuncionarioDto insertFuncionarioDto) throws CidadaoInvalido, StatusInvalido, TipoLoginInvalido;
}
