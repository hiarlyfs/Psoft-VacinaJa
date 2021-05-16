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
import com.vacinasja.event.estado_vacinacao.EstadoVacinacaoAtualizadoEvent;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.model.LoteVacina;
import com.vacinasja.model.Vacina;
import com.vacinasja.repository.CidadaoRepository;
import com.vacinasja.repository.CidadaoVacinacaoRepository;
import com.vacinasja.service.login_cidadao_service.LoginCidadaoService;
import com.vacinasja.service.lote_vacina_service.LoteVacinaService;
import com.vacinasja.service.vacina_service.VacinaService;

import com.vacinasja.utils.validacoes.ValidaCPF;
import com.vacinasja.utils.validacoes.ValidaEMAIL;
import com.vacinasja.utils.validacoes.ValidaTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CidadaoServiceImpl implements CidadaoService {

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

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Override
	public LoginCidadao save(InsertCidadaoDto insertCidadaoDto)
			throws ParseException, TipoLoginInvalido, CPFInvalido, EmailInvalido, TelefoneInvalido {
		Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(insertCidadaoDto.getDataNascimento());
		ValidaCPF.validaCPF(insertCidadaoDto.getCpf());
		ValidaEMAIL.validaEMAIL(insertCidadaoDto.getEmail());
		ValidaTelefone.validaTelefone(insertCidadaoDto.getTelefone());
		Cidadao novoCidadao = new Cidadao(insertCidadaoDto.getNomeCompleto(), insertCidadaoDto.getEndereco(),
				insertCidadaoDto.getCpf(), insertCidadaoDto.getCartaoSus(), insertCidadaoDto.getEmail(), dataNascimento,
				insertCidadaoDto.getTelefone(), insertCidadaoDto.getProfissao(), insertCidadaoDto.getCormobidades());
		cidadaoRepository.save(novoCidadao);
		return loginCidadaoService.criarLoginCidadao(novoCidadao);
	}

	@Override
	public Cidadao update(String cartaoSus, UpdateCidadaoDto updateCidadaoDto)
			throws CidadaoNaoEncontradoCartaoSus, EmailInvalido, TelefoneInvalido {
		Cidadao cidadao = findByCartaoSus(cartaoSus);
		ValidaEMAIL.validaEMAIL(updateCidadaoDto.getEmail());
		ValidaTelefone.validaTelefone(updateCidadaoDto.getTelefone());
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
	public CidadaoVacinacao registrarVacinacao(CidadaoVacinacaoDto cidadaoVacinacaoDto) throws ParseException,
			LoteVacinaInexistente, VacinaInexistente, CidadaoNaoEncontradoCpf, CidadaoNaoHabilitado {
		Date dataVacinacao = new SimpleDateFormat("yyyy-MM-dd")
				.parse(cidadaoVacinacaoDto.getDataVacinacao().toString());
		Vacina vacina = vacinaService.getVacinaById(cidadaoVacinacaoDto.getTipoVacina());
		LoteVacina lote = loteService.getLoteVacinaById(cidadaoVacinacaoDto.getLote());
		CidadaoVacinacao novoRegistro = new CidadaoVacinacao(cidadaoVacinacaoDto.getCpf(), dataVacinacao, lote, vacina,
				cidadaoVacinacaoDto.getNumDose());
		Cidadao cidadao = findByCpf(cidadaoVacinacaoDto.getCpf());
		if (cidadao.vacinar(vacina)) {
			cidadaoRepository.save(cidadao);
			cidadaoVacinacaoRepository.save(novoRegistro);
			return novoRegistro;
		} else {
			throw new CidadaoNaoHabilitado(cidadao.getNome());
		}
	}

	@Override
	public List<Cidadao> habilitarByProfissao(String profissao) {
		List<Cidadao> cidadaos = cidadaoRepository.findByProfissao(profissao);
		List<LoteVacina> numLotes = loteService.allLotesVacina();
		List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
		int numDoses = 0;

		for (LoteVacina lotes : numLotes) {
			numDoses += lotes.getQtdDoses();
		}

		for (int i = 0; i < numDoses; i++) {
			if (i > cidadaos.size())
				break;
			Cidadao cidadao = cidadaos.get(i);
			if (cidadao.passarEstagioByProfissao(profissao)) {
				cidadaoRepository.save(cidadao);
				applicationEventPublisher.publishEvent(new EstadoVacinacaoAtualizadoEvent(cidadao));
				cidadaosHabilitados.add(cidadao);
			}
		}

		return cidadaosHabilitados;
	}

	@Override
	public List<Cidadao> habilitarByComorbidade(String comorbidade) {
		List<Cidadao> cidadaos = cidadaoRepository.findByComorbidade(comorbidade);
		List<LoteVacina> numLotes = loteService.allLotesVacina();
		List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
		int numDoses = 0;

		for (LoteVacina lotes : numLotes) {
			numDoses += lotes.getQtdDoses();
		}

		for (int i = 0; i < numDoses; i++) {
			if (i > cidadaos.size())
				break;
			Cidadao cidadao = cidadaos.get(i);
			if (cidadao.passarEstagioByComorbidade(comorbidade)) {
				cidadaoRepository.save(cidadao);
				applicationEventPublisher.publishEvent(new EstadoVacinacaoAtualizadoEvent(cidadao));
				cidadaosHabilitados.add(cidadao);
			}
		}

		return cidadaosHabilitados;
	}

	@Override
	public List<Cidadao> habilitarByIdade(Integer idade) {
		LocalDate dataAtual = LocalDate.now();
		LocalDate data = dataAtual.minusYears(idade);
		Date dataNascimento = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<Cidadao> cidadaos = cidadaoRepository.findByDataNascimentoLessThanEqual(dataNascimento);
		List<LoteVacina> numLotes = loteService.allLotesVacina();
		List<Cidadao> cidadaosHabilitados = new ArrayList<Cidadao>();
		int numDoses = 0;

		for (LoteVacina lotes : numLotes) {
			numDoses += lotes.getQtdDoses();
		}

		for (int i = 0; i < numDoses; i++) {
			if (i > cidadaos.size())
				break;
			Cidadao cidadao = cidadaos.get(i);
			if (cidadao.passarEstagioByIdade(idade)) {
				cidadaoRepository.save(cidadao);
				applicationEventPublisher.publishEvent(new EstadoVacinacaoAtualizadoEvent(cidadao));
				cidadaosHabilitados.add(cidadao);
			}
		}

		return cidadaosHabilitados;
	}

}
