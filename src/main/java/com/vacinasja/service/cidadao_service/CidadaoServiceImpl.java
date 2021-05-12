package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontrado;
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
import java.util.Optional;

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

    @Override
    public Cidadao update(String cpf, UpdateCidadaoDto updateCidadaoDto) throws CidadaoNaoEncontrado {
        // atualizar para pegar o cidadão da sessão atual.
        Cidadao cidadao = findByCpf(cpf);
        // fazer validação de formato dos dados -> email / telefone / nome nao nulo

        cidadao.setComorbidade(updateCidadaoDto.getCormobidades());
        cidadao.setEmail(updateCidadaoDto.getEmail());
        cidadao.setProfissao(updateCidadaoDto.getProfissao());
        cidadao.setTelefone(updateCidadaoDto.getTelefone());
        cidadao.setEndereco(updateCidadaoDto.getEndereco());
        cidadao.setNome(updateCidadaoDto.getNomeCompleto());
        cidadaoRepository.save(cidadao);

        return cidadao;
    }

    public String listaEstagioCidadao(String cpf) throws CidadaoNaoEncontrado {
        Optional<Cidadao> cidadao = cidadaoRepository.findByCpf(cpf);

        if (!cidadao.isPresent()) {
            throw new CidadaoNaoEncontrado(cpf);
        }

        return cidadao.get().getEstagioVacinacao(); // String de representação do estágio como na especificação.
    }

    @Override
    public Cidadao findByCpf(String cpf) throws CidadaoNaoEncontrado {
        Optional<Cidadao> cidadao = cidadaoRepository.findByCpf(cpf);

        if (!cidadao.isPresent()) {
            throw new CidadaoNaoEncontrado(cpf);
        }

        return cidadao.get();

    }
}
