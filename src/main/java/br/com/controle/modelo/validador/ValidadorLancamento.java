package br.com.controle.modelo.validador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controle.modelo.dao.LancamentoDao;
import br.com.controle.modelo.negocio.Lancamento;


@Named
@RequestScoped
public class ValidadorLancamento {

	private String mensagem;
	@Inject
	private LancamentoDao lancamentoDao;	

	public boolean naoPodeIncluir(Lancamento lancamento) {
		
		boolean teste = false;
		
		if (lancamento.getDescricao().isEmpty()) {
			mensagem = "Descrição não foi informado";
			teste = true;
			return teste;
		}
		
		if (lancamento.getValor()==0) {
			mensagem = "valor deve ser informado";
			teste = true;
			return teste;
		}
	
		

		if (lancamento.getValor()<0) {
			mensagem = "valor informado deve ser positivo";
			teste = true;
			return teste;
		}
				return teste;
	}

	public String getMensagem() {
		return mensagem;
	}

}
