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
	private String novaSenha;
	private Integer progress;
	 int autenticado=0;
	    
	
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public JmeFuncionario getFuncionarioLogado() {
		if (funcionarioLogado==null){
			funcionarioLogado= new JmeFuncionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(JmeFuncionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public Integer getProgress() {
		if(progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int)(Math.random() * 85);
             
            if(progress > 100)
                progress = 100;
            	
            
        }
         
        return progress;
    }
	
	public void executarProgress(){
		
		
	}
	
	public String autenticar(){
		
		try{
		JmeFuncionarioDAO daofuncioanrio = new JmeFuncionarioDAO();
		
		funcionarioLogado = daofuncioanrio.autenticar(funcionarioLogado.getCpf(),DigestUtils.md5Hex(funcionarioLogado.getSenha()));
		
		if(funcionarioLogado==null){
			progress=70;
			autenticado=0;
			//JSFUtil.adicionarMensagemErro("CPF e/ou senha inválidos");
			
			
		}else{
			autenticado=1;
			progress=70;
			return "/pages/jmePrincipal.xhtml?faces-redirect=true";
			//JSFUtil.adicionarMensagemSucesso("Funcionário autenticado com sucesso");
			
		}
		progress = 0;
	
		}catch(RuntimeException e){
			JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema:"+ e.getMessage());
		}
		return null;
	}
	
	public String exibirMesagem(){
		if(autenticado==0){
			
			JSFUtil.adicionarMensagemErro("CPF e/ou senha inválidos");
			
			progress=70;
		}else{
			progress=70;
			
			
			
		}
		
		return null;
	}
	
	//se precisa navegar entre tela é action, agora se precisa permanecer na mesma tela utiliza actionListener
	// e o metodo para action não pode ser void
	public String sair(){
		funcionarioLogado=null;
		return "/pages/jmeAutenticacao.xhtml?faces-redirect=true";
	}
	
	public String alterarSenha (){
		try{
			JmeFuncionarioDAO daofuncioanrio = new JmeFuncionarioDAO();
			
			funcionarioLogado = daofuncioanrio.autenticar(funcionarioLogado.getCpf(),DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			
			if(funcionarioLogado==null){
				JSFUtil.adicionarMensagemErro("CPF e/ou senha inválidos");
			}else{
				
				funcionarioLogado.setSenha(DigestUtils.md5Hex(novaSenha));
				daofuncioanrio.editar(funcionarioLogado);
				JSFUtil.adicionarMensagemSucesso("Senha Alterada com sucesso!");
				return "/pages/jmePrincipal.xhtml?faces-redirect=true";
			}
			
		
			}catch(RuntimeException e){
				JSFUtil.adicionarMensagemErro("Erro ao alterar a senha:"+ e.getMessage());
			}
			return null;
		
	}
	
	
}
