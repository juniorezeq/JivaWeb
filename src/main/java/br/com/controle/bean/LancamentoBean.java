package br.com.controle.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.faces.view.ViewScoped;
//import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controle.modelo.dao.LancamentoDao;
import br.com.controle.modelo.negocio.Lancamento;
import br.com.controle.modelo.negocio.TipoLancamento;
import br.com.controle.modelo.tx.Transacional;
import br.com.controle.modelo.validador.ValidadorLancamento;

@Named
@ViewScoped
public class LancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoDao lancamentoDao;
	private Lancamento lancamento;
	private Lancamento selecionado;
	private ValidadorLancamento validador;

	@PostConstruct
	public void init() {
		lancamento = new Lancamento();
		System.out.println("LancamentoBean.init();");
	}

	@Transacional
	public String novo() {
		return "/view/lancamento.xhtml?faces-redirect=true";
	}

	@Transacional
	public String home() {
		return "/view/index.xhtml?faces-redirect=true";
	}

	@Transacional
	public void gravar() {

		try {
			if (lancamento.getTipolacamento().equals(TipoLancamento.Pagar)) {
				lancamento.setValor(lancamento.getValor() * -1);
			}
			lancamentoDao.adiciona(lancamento);
			mensagemSucesso("Adicionado com sucesso");
			lancamento = new Lancamento();
		} catch (Exception e) {
			mensagemErro("não foi possivel  gravar");
		}
	}

	@Transacional
	public void apagar(Lancamento selecionado) {
		try {
			lancamentoDao.remove(selecionado);
			mensagemSucesso("apagado com sucesso");
			System.out.println("apagado o registro");
		} catch (Exception e) {
			mensagemErro("Erro não foi possivel apagar");
			System.out.println("Erro não foi possivel apagar");
		}
	}

	@Transacional
	public List<Lancamento> listarTodos() {
		List<Lancamento> todos = new ArrayList<Lancamento>();
		todos = lancamentoDao.listaTodos();
		return todos;
	}

	@Transacional
	public void atualizaLancamento() {
		try {
			lancamentoDao.atualiza(lancamento);
			mensagemSucesso(" Gravado com sucesso");

		} catch (Exception e) {
			mensagemErro("Erro não foi possivel atualizar");
		}
	}

	@Transacional
	public void limpar() {
		lancamento = new Lancamento();
	}

	public Lancamento getConta() {
		return lancamento;
	}

	public void setConta(Lancamento conta) {
		this.lancamento = conta;
	}

	private void mensagemSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!!", mensagem));
	}

	private void mensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", mensagem));

	}

	public Lancamento getSelecionado() {
		return selecionado;
	}

	public void onSelect(Lancamento selecionado) {
		this.lancamento = selecionado;
		System.out.println("Registro Selecionado: " + lancamento.getDescricao());
	}

	public void onDeselect() {

	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public void setSelecionado(Lancamento selecionado) {
		this.selecionado = selecionado;
	}

}
