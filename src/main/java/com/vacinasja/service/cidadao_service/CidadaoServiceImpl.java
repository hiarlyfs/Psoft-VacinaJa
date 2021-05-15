package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.dto.cidadao_vacina.CidadaoVacinacaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.model.LoteVacina;
import com.vacinasja.model.Vacina;
import com.vacinasja.model.statecidadao.NaoHabilitado;
import com.vacinasja.model.statecidadao.Tomou1Dose;
import com.vacinasja.repository.CidadaoRepository;
import com.vacinasja.repository.CidadaoVacinacaoRepository;
import com.vacinasja.service.login_cidadao_service.LoginCidadaoService;
import com.vacinasja.service.lote_vacina_service.LoteVacinaService;
import com.vacinasja.service.vacina_service.VacinaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CidadaoServiceImpl implements  CidadaoService{

    @Autowired
    CidadaoRepository cidadaoRepository;
    
    @Autowired
    CidadaoVacinacaoRepository cidadaoVacinacaoRepository;
    
    @Autowired
    LoteVacinaService loteService;
    

    @Autowired
    LoginCidadaoService loginCidadaoService;

    @Autowired
    VacinaService vacinaService;
    
        

    
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
    public Cidadao update(String cartaoSus, UpdateCidadaoDto updateCidadaoDto) throws CidadaoNaoEncontradoCartaoSus {
        Cidadao cidadao = findByCartaoSus(cartaoSus);
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

    public String listaEstagioCidadao(String cartaoSus) throws CidadaoNaoEncontradoCartaoSus {
        Cidadao cidadao = findByCartaoSus(cartaoSus);
        return cidadao.getEstagioVacinacao(); // String de representação do estágio como na especificação.
    }

    @Override
    public Cidadao findByCpf(String cpf) throws CidadaoNaoEncontradoCpf {
        Optional<Cidadao> cidadao = cidadaoRepository.findByCpf(cpf);

        if (!cidadao.isPresent()) {
            throw new CidadaoNaoEncontradoCpf(cpf);
        }

        return cidadao.get();

    }

    @Override
    public Cidadao findByCartaoSus(String cartaoSus) throws CidadaoNaoEncontradoCartaoSus {
        Optional<Cidadao> cidadao = cidadaoRepository.findByCartaoSus(cartaoSus);

        if (!cidadao.isPresent()) {
            throw new CidadaoNaoEncontradoCartaoSus(cartaoSus);
        }

        return cidadao.get();
    }
    
    @Override
    public CidadaoVacinacao  registrarVacinacao(CidadaoVacinacaoDto cidadaoVacinacaoDto) throws ParseException, TipoLoginInvalido, LoteVacinaInexistente, VacinaInexistente {
        Date dataVacinacao = new SimpleDateFormat("yyyy-MM-dd").parse(cidadaoVacinacaoDto.getDataVacinacao().toString());
        Vacina vacina = vacinaService.getVacinaById(cidadaoVacinacaoDto.getTipoVacina());
        LoteVacina lote = loteService.getLoteVacinaById(cidadaoVacinacaoDto.getLote());
        
        
        CidadaoVacinacao novoRegistro= new CidadaoVacinacao(cidadaoVacinacaoDto.getCpf(), dataVacinacao,
                lote, vacina, cidadaoVacinacaoDto.getNumDose());
        cidadaoVacinacaoRepository.save(novoRegistro);
        return novoRegistro;
    }
    
    
    @Override
    public List<Cidadao> habilitarByProfissao(String profissao) {
        List<Cidadao> cidadaos = cidadaoRepository.findByProfissao(profissao);
        
        for (Cidadao cidadao : cidadaos) {
        	System.out.println(cidadao);
 		   if (cidadao.getEstadoVacinacao()  instanceof NaoHabilitado) {
 			   cidadao.passarEstagioByProfissao(profissao);   
 		   } else if (cidadao.getEstadoVacinacao() instanceof Tomou1Dose) {
 			   LocalDate dataAtual = LocalDate.now();			   
 			   cidadao.passarEstagio(dataAtual);
 		   }
        }
        return cidadaos;
    }


	@Override
	public List<Cidadao> habilitarByComorbidade(String comorbidade) {
		List<Cidadao> cidadaos = cidadaoRepository.findByComorbidade(comorbidade);
        for (Cidadao cidadao : cidadaos) {
 		   if (cidadao.getEstadoVacinacao()  instanceof NaoHabilitado) {
 			   cidadao.passarEstagioByComorbidade(comorbidade);   
 		   } else if (cidadao.getEstadoVacinacao() instanceof Tomou1Dose) {
 			   LocalDate dataAtual = LocalDate.now();			   
 			   cidadao.passarEstagio(dataAtual);
 		   }
        }
        return cidadaos;
	}


	@Override
	public List<Cidadao> habilitarByIdade(Integer idade) {
		LocalDate dataAtual = LocalDate.now();
		LocalDate data= dataAtual.minusYears(idade);
		Date dataNascimento = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		List<Cidadao> cidadaos = cidadaoRepository.findByDataNascimentoLessThanEqual(dataNascimento);
        for (Cidadao cidadao : cidadaos) {
 		   if (cidadao.getEstadoVacinacao()  instanceof NaoHabilitado) {
 			   cidadao.passarEstagioByIdade(idade);   
 		   } else if (cidadao.getEstadoVacinacao() instanceof Tomou1Dose) {
 			   			   
 			   cidadao.passarEstagio(dataAtual);
 		   }
        }
        return cidadaos;
	}
    
}
