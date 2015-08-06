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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;



@Entity
@Table(name="Fornecedor")
@NamedQueries({
	  @NamedQuery(name="JmeFornecedor.buscarPorCodigo", 
			  	  query= "SELECT Fornecedor FROM JmeFornecedor Fornecedor WHERE FOR_ID=:idFornecedor"),
	  @NamedQuery(name="JmeFornecedor.listar",
			  	  query="SELECT Fornecedor FROM JmeFornecedor Fornecedor")})
public class JmeFornecedor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*Chave primaria do Fornecedor*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FOR_ID")
	private Integer idFornecedor;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name="FOR_DATA_CADASTRO")
	private Date dataCadastro;
	
	@Column(name="FOR_RAZAO_SOCIAL")
	@NotEmpty(message="O campo razao social � obrigat�rio")
	@Size(min=4, max=50, message="Tamanho inv�lido para o campo endere�o(3 - 50) caracteres")
	private String razaoSocial;
	
	@CNPJ(message="O campo CNPJ � inv�lido")
	@NotEmpty(message="O campo CNPJ � obrigat�rio")
	@Column(name="FOR_CNPJ")
	private String cnpj;
	
	@Column(name="FOR_NOME_FANTASIA")
	@Size(min=4, max=50, message="Tamanho inv�lido para o campo endere�o(3 - 50) caracteres")
	@NotEmpty(message="O campo nome fantasia � obrigat�rio")
	private String nomeFantasia;
	
	@Column(name="FOR_TELEFONE_FIXO")
	@NotEmpty(message="O campo telefone fixo � obrigat�rio")
	private String telefoneFixo;
	
	@Column(name="FOR_EMAIL")
	@NotEmpty(message="O campo email � obrigat�rio")
	@Email(message="O campo email � inv�lido")
	private String email;
	
	@Column(name="FOR_ENDERECO")
	@NotEmpty(message="O campo endere�o � obrigat�rio")
	private String endereco;
	
	@Column(name="FOR_CEP")
	@NotEmpty(message="O campo cep � obrigat�rio")
	private String cep;
	
	@Column(name="FOR_CAIXA_POSTAL")
	private Integer caixaPostal;
	
	/*Chave estrangeira da Cidade, uma Cidade tem varios Fornecedores*/
	/*Many= Muitos fornecedores podem estar em One= Cidade*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="FOR_CID_ID", referencedColumnName="CID_ID", nullable=false)
	private JmeCidade cidade = new JmeCidade(); 
	
	
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Integer getCaixaPostal() {
		return caixaPostal;
	}
	public void setCaixaPostal(Integer caixaPostal) {
		this.caixaPostal = caixaPostal;
	}
	public JmeCidade getJmeCidade() {
		return cidade;
	}
	public void setJmeCidade(JmeCidade cidade) {
		this.cidade = cidade;
	}
	

	@Override
	public String toString(){
		return "JmeFornecedor[idFornecedor="+idFornecedor+", dataCadastro="+dataCadastro+
				", razaoSocial="+razaoSocial+", cnpj="+cnpj+", nomeFantasia="+nomeFantasia+
				", telefoneFixo="+telefoneFixo+", email="+email+", endereco="+endereco+
				", cep="+cep+", caixaPostal="+caixaPostal+", cidade="+cidade+"]";
	}

}
