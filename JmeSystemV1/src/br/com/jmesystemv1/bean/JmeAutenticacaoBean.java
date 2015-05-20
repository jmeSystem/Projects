package br.com.jmesystemv1.bean;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;





import org.apache.commons.codec.digest.DigestUtils;

import br.com.jmesystemv1.dao.JmeFuncionarioDAO;
import br.com.jmesystemv1.domain.JmeFuncionario;
import br.com.jmesystemv1.util.JSFUtil;

@SuppressWarnings("serial")
@ManagedBean(name="MBAutentificacao")
@SessionScoped // que dizer que ele vai existir durante todo tempo de sessao do usuario
public class JmeAutenticacaoBean implements Serializable{

	private JmeFuncionario funcionarioLogado;

	public JmeFuncionario getFuncionarioLogado() {
		if (funcionarioLogado==null){
			funcionarioLogado= new JmeFuncionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(JmeFuncionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public String autenticar(){
		
		try{
		JmeFuncionarioDAO daofuncioanrio = new JmeFuncionarioDAO();
		
		funcionarioLogado = daofuncioanrio.autenticar(funcionarioLogado.getCpf(),DigestUtils.md5Hex(funcionarioLogado.getSenha()));
		
		if(funcionarioLogado==null){
			JSFUtil.adicionarMensagemErro("CPF e/ou senha inválidos");
		}else{
			JSFUtil.adicionarMensagemSucesso("Funcionário autenticado com sucesso");
			return "/pages/jmePrincipal.xhtml?faces-redirect=true";
		}
		
	
		}catch(RuntimeException e){
			JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema:"+ e.getMessage());
		}
		return null;
	}
	
	//se precisa navegar entre tela é action, agora se precisa permanecer na mesma tela utiliza actionListener
	// e o metodo para action não pode ser void
	public String sair(){
		funcionarioLogado=null;
		return "/pages/jmeAutenticacao.xhtml?faces-redirect=true";
	}
}
