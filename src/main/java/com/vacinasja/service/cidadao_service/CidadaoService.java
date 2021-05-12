package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontrado;
import com.vacinasja.error.cidadao_error.CidadaoInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;

import java.text.ParseException;

public interface CidadaoService {
    LoginCidadao save(InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido;


    String listaEstagioCidadao(String cpf) throws CidadaoNaoEncontrado;
    Cidadao findByCpf(String cpf) throws CidadaoInvalido;
}
