package com.vacinasja.service.funcionario_service;

import com.vacinasja.dto.funcionario.InsertFuncionarioDto;
import com.vacinasja.error.cidadao_error.CidadaoInvalido;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Funcionario;
import com.vacinasja.model.LoginFuncionario;
import com.vacinasja.model.StatusCadastro;
import com.vacinasja.repository.FuncionarioRepository;
import com.vacinasja.service.cidadao_service.CidadaoService;
import com.vacinasja.service.login_funcionario_service.LoginFuncionarioService;
import com.vacinasja.service.status_cadastro_service.StatusCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    @Autowired
    CidadaoService cidadaoService;

    @Autowired
    LoginFuncionarioService loginFuncionarioService;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    StatusCadastroService statusCadastroService;

    @Override
    public LoginFuncionario save(InsertFuncionarioDto insertFuncionarioDto) throws CidadaoInvalido, StatusInvalido, TipoLoginInvalido {
        Cidadao cidadao = cidadaoService.findByCpf(insertFuncionarioDto.getCpf());
        StatusCadastro statusCadastro = statusCadastroService.findByStatus("PENDENTE");
        Funcionario funcionario = new Funcionario(cidadao, insertFuncionarioDto.getCargo(), insertFuncionarioDto.getLocalTrabalho(), statusCadastro);
        funcionarioRepository.save(funcionario);
        return loginFuncionarioService.save(funcionario);
    }
}
