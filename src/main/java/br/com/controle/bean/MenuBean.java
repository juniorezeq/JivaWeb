package br.com.controle.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.faces.view.ViewScoped;
//import javax.enterprise.context.RequestScoped;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.controle.modelo.negocio.Lancamento;
import br.com.controle.modelo.tx.Transacional;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamento lancamento;

	@PostConstruct
	public void init() {
		lancamento = new Lancamento();
		System.out.println("menuBean.init();");
	}

	@Transacional
	public String home() {
		return "/JivaWeb/view/index.xhtml?faces-redirect=true";
	}

	@Transacional
	public String sankhya() {
		return "/JivaWeb/view/sankhya.xhtml?faces-redirect=true";
	}

	@Transacional
	public String lancamento() {
		return "/JivaWeb/view/lancamento.xhtml?faces-redirect=true";
	}

}
