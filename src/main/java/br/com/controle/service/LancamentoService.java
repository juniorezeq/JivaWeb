package br.com.controle.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.controle.modelo.negocio.Lancamento;
import br.com.controle.repository.Lancamentos;
import br.com.controle.util.Transacional;


public class LancamentoService implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Inject
	private Lancamentos lancamentos;
	
    @Transacional
	public Lancamento salvar(Lancamento lancamento) {
	    return lancamentos.salvar(lancamento);
	}
	
    @Transacional
	public void remover(Lancamento lancamento) {
	    lancamentos.remover(lancamento);
	}
}