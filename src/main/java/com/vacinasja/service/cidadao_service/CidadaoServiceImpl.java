package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.repository.CidadaoRepository;
import com.vacinasja.service.login_cidadao_service.LoginCidadaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CidadaoServiceImpl implements  CidadaoService{

    @Autowired
    CidadaoRepository cidadaoRepository;

    @Autowired
    LoginCidadaoService loginCidadaoService;

    @Override
    public LoginCidadao save(InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido {
        Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(insertCidadaoDto.getDataNascimento());
        Cidadao novoCidadao = new Cidadao(insertCidadaoDto.getNomeCompleto(), insertCidadaoDto.getEndereco(),
                insertCidadaoDto.getCpf(), insertCidadaoDto.getCartaoSus(), insertCidadaoDto.getEmail(), dataNascimento,
                insertCidadaoDto.getTelefone(), insertCidadaoDto.getProfissao(), insertCidadaoDto.getCormobidades());

        cidadaoRepository.save(novoCidadao);
        return loginCidadaoService.criarLoginCidadao(novoCidadao);
    }
}
