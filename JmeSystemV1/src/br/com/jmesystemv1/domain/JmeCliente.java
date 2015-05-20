package br.com.jmesystemv1.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="Cliente")
@NamedQueries({ 
@NamedQuery(name="JmeCliente.listar", 
			query="select cliente from JmeCliente cliente"),
@NamedQuery(name="JmeCliente.buscarPorCodigo", 
			query="select cliente from JmeCliente cliente where cli_id =:idCliente")})
public class JmeCliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	// o campo codigo e a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO)
	// e auto incremento
	@Column(name = "cli_id")
	private Integer idCliente;
	
	@ManyToOne(fetch = FetchType.EAGER) //muitas vendas pertence a muitos funcionÃ¡rios
	@JoinColumn(name="cli_cid_id", referencedColumnName="cid_id", nullable=false)
	private JmeCidade cidade = new JmeCidade();
	
	@Column(name="cli_nome")
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=3, max=50, message="Tamanho inválido para o campo nome (3 - 50) caracteres")
	private String nome;
	
	@Column(name="cli_endereco")
	@NotEmpty(message="O campo endereço é obrigatório")
	@Size(min=6, max=50, message="Tamanho inválido para o campo endereço(3 - 50) caracteres")
	private String endereco;
	
	@Temporal(value = TemporalType.DATE) // que dizer que e um campo do tipo data e horario
	@Column(name="cli_data_nasc")
	private Date dataNascimento;
	
	@Column(name="cli_telefone")
	@NotEmpty(message="O campo telefone é obrigatório")
	private String telefone;
	
	@Temporal(value = TemporalType.DATE) // que dizer que e um campo do tipo data e horario
	@Column(name="cli_data_cadastro")
	private Date dataCadastro;
	
	@Column(name="cli_cpf", unique=true)
	@NotEmpty(message="O campo CPF é obrigatório")
	@CPF(message="CPF informado é inválido")
	private String cpf;
	
	@Column(name="cli_tel_trabalho")
	private String telefoneTrabalho;
	
	@Column(name="cli_info_complementar")
	private String inforComplementar;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public JmeCidade getCidade() {
		return cidade;
	}

	public void setCidade(JmeCidade cidade) {
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefoneTrabalho() {
		return telefoneTrabalho;
	}

	public void setTelefoneTrabalho(String telefoneTrabalho) {
		this.telefoneTrabalho = telefoneTrabalho;
	}

	public String getInforComplementar() {
		return inforComplementar;
	}

	public void setInforComplementar(String inforComplementar) {
		this.inforComplementar = inforComplementar;
	}

	@Override
	public String toString() {
		return "JmeCliente [idCliente=" + idCliente + ", cidade=" + cidade
				+ ", nome=" + nome + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + ", telefone="
				+ telefone + ", dataCadastro=" + dataCadastro + ", cpf=" + cpf
				+ ", telefoneTrabalho=" + telefoneTrabalho
				+ ", inforComplementar=" + inforComplementar + "]";
	}
	
	
}
