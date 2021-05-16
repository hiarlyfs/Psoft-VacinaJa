package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.dto.cidadao_vacina.CidadaoVacinacaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.cidadao_error.CidadaoNaoHabilitado;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.error.verificacoes_error.CPFInvalido;
import com.vacinasja.error.verificacoes_error.EmailInvalido;
import com.vacinasja.error.verificacoes_error.TelefoneInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;
import com.vacinasja.model.LoginCidadao;

import java.text.ParseException;
import java.util.List;

public interface CidadaoService {
    LoginCidadao save(InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido, CPFInvalido, EmailInvalido, TelefoneInvalido;
    Cidadao update(String cpf, UpdateCidadaoDto updateCidadaoDto) throws CidadaoNaoEncontradoCartaoSus, EmailInvalido, TelefoneInvalido;
    String listaEstagioCidadao(String cpf) throws CidadaoNaoEncontradoCartaoSus;
    Cidadao findByCpf(String cpf) throws CidadaoNaoEncontradoCpf;
    Cidadao findByCartaoSus(String cartaoSus) throws CidadaoNaoEncontradoCartaoSus;
    CidadaoVacinacao registrarVacinacao(CidadaoVacinacaoDto cidadaoVacinacao) throws ParseException, LoteVacinaInexistente, VacinaInexistente, CidadaoNaoEncontradoCpf, CidadaoNaoHabilitado;
    List<Cidadao> habilitarByProfissao(String profissao);
    List<Cidadao> habilitarByComorbidade(String comorbidade);
    List<Cidadao> habilitarByIdade(Integer idade);
}
