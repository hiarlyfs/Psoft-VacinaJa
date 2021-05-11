package com.vacinasja.service.login_funcionario_service;

import com.vacinasja.dto.login_funcionario.AuthorizeFuncionarioDto;
import com.vacinasja.error.login_error.AguardandoAprovacao;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.*;
import com.vacinasja.repository.LoginFuncionarioRepository;
import com.vacinasja.service.login_service.LoginService;
import com.vacinasja.service.status_cadastro_service.StatusCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginFuncionarioServiceImpl implements LoginFuncionarioService{

    @Autowired
    LoginService loginService;

    @Autowired
    LoginFuncionarioRepository loginFuncionarioRepository;

    @Autowired
    StatusCadastroService statusCadastroService;

    @Override
    public LoginFuncionario save(Funcionario funcionario) throws TipoLoginInvalido, StatusInvalido {
        Login novoLogin = loginService.criarLogin(funcionario.getCpf(), "FUNCIONARIO");
        StatusCadastro statusCadastro = statusCadastroService.findByStatus("PENDENTE");
        LoginFuncionario novoLoginFuncionario = new LoginFuncionario(novoLogin, funcionario, statusCadastro);
        loginFuncionarioRepository.save(novoLoginFuncionario);
        return novoLoginFuncionario;
    }

    @Override
    public LoginFuncionario findByLogin(Login login) {
        Optional<LoginFuncionario> loginFuncionario = loginFuncionarioRepository.findByLogin(login);
        return loginFuncionario.orElse(null);
    }

    @Override
    public void checkAuthorizeToLogin(Login login) throws AguardandoAprovacao {
        LoginFuncionario loginFuncionario = findByLogin(login);

        if (loginFuncionario.getStatusCadastro() != "CONFIRMADO") {
            throw new AguardandoAprovacao();
        }
    }

    @Override
    public List<Map<String, Object>> getLoginsToAuthorize() throws StatusInvalido {
        StatusCadastro statusCadastro = statusCadastroService.findByStatus("PENDENTE");
        List<LoginFuncionario> funcionarios = loginFuncionarioRepository.findByStatusCadastro(statusCadastro);

        List<Map<String, Object>> funcionariosData = new ArrayList<>();
        funcionarios.forEach(funcionario -> {
            Map<String, Object> funcionarioData = parseFuncionarioLoginData(funcionario);
            funcionariosData.add(funcionarioData);
        });

        return funcionariosData;
    }

    @Override
    public void authorizeFuncionarios(List<AuthorizeFuncionarioDto> authorizeFuncionariosDto) throws StatusInvalido {
        StatusCadastro novoStatusCadastro = statusCadastroService.findByStatus("CONFIRMADO");

        authorizeFuncionariosDto.forEach(dto -> {
            authorizeFuncionarioByLogin(novoStatusCadastro, dto.getLogin());
        });
    }

    private void authorizeFuncionarioByLogin(StatusCadastro novoStatusCadastro, String loginFuncionario) {
        Login login = loginService.findByUserLogin(loginFuncionario);
        Optional<LoginFuncionario> loginFuncionarioOptional = loginFuncionarioRepository.findByLogin(login);

        if (loginFuncionarioOptional.isPresent()) {
            LoginFuncionario loginFuncionarioExistente = loginFuncionarioOptional.get();
            loginFuncionarioExistente.setStatusCadastro(novoStatusCadastro);
            loginFuncionarioRepository.save(loginFuncionarioExistente);
        }
    }


    private Map<String, Object>  parseFuncionarioLoginData(LoginFuncionario funcionario) {
        Map<String, Object> fData = new HashMap<>();
        fData.put("id", funcionario.getId());
        fData.put("login", funcionario.getLogin().getLogin());
        fData.put("statusCadastrro", funcionario.getStatusCadastro());
        return fData;
    }
}
