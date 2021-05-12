package com.vacinasja.model;

import com.sun.istack.NotNull;
import com.vacinasja.model.statecidadao.Estado;
import com.vacinasja.model.statecidadao.NaoHabilitado;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
public class Cidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String endereco;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Column(unique = true)
    private String cartaoSus;

	@NotNull
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String telefone;
    private String profissao;

    @ElementCollection
    @CollectionTable(name = "cidadao_comorbidade", joinColumns = @JoinColumn(name = "cidadao_id"))
    @JoinColumn(name = "cidadao_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<String> comorbidade;

    @OneToOne(cascade = CascadeType.ALL)
    private Estado estagioVacinacao;

    @Temporal(TemporalType.DATE)
    private Date diaTomouDose;
    private Integer intervaloDoses;

    public Cidadao() {

    }

    public Cidadao(String nome, String endereco, String cpf, String cartaoSus, String email, Date dataNascimento, String telefone, String profissao, List<String> comorbidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cartaoSus = cartaoSus;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.profissao = profissao;
        this.comorbidade = comorbidade;
        this.estagioVacinacao = new NaoHabilitado();
        this.diaTomouDose = null;
        this.intervaloDoses = 0;
    }

    public String getCpf() {
        return cpf;
    }


    public String getProfissao() {
        return profissao;
    }

    public int getIdade() {
        return Period.between(dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }

    public List<String> getComorbidades() {
        return comorbidade;
    }

    public void setDiaTomouDose(Date dia) {
        this.diaTomouDose = dia;
    }

    public Date getDiaTomouDose() {
        return diaTomouDose;
    }

    public void setIntervaloDoses(Integer intervaloDoses) {
        this.intervaloDoses = intervaloDoses;
    }

    public Integer getIntervaloDoses() {
        return intervaloDoses;
    }

    // a ser utilizado pela notificação de alteração do funcionário (para habilitar não habilitados)
    public void passarEstagio(Integer idade, String profissao, String comorbidade) {
        estagioVacinacao.tentaAlterar(this, idade, profissao, comorbidade);
    }

    // a ser utilizado pela notificação de alteração diária do sistema (para habilitar para segunda dose quem tomou a primeira) // data = LocalDate.now()
    public Boolean passarEstagio(LocalDate data) {
        return estagioVacinacao.tentaAlterar(this, data);
    }

    // a ser utilizado pelo funcionário para registrar a vacinação do paciente (só é realizado para cidadãos habilitados)
    public void vacinar(Vacina vacina) {
        estagioVacinacao.tentaAlterar(this, vacina);
    }

    public void setNewEstagioVacinacao(Estado newEstagioVacinacao) {
        this.estagioVacinacao = newEstagioVacinacao;
    }

    // usado para retornar o estágio atual de vacinação do cidadão
    public String getEstagioVacinacao() {
        return estagioVacinacao.toString();
    }

    public String getEmail() {
		return email;
	}
    
    public String getNome() {
		return nome;
	}

	public String getCartaoSus() {
        return cartaoSus;
    }
}
