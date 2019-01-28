package br.com.controle.bean;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.controle.modelo.dao.LancamentoDao;
import br.com.controle.modelo.negocio.Lancamento;
import br.com.controle.modelo.negocio.TipoLancamento;
import br.com.controle.util.Transacional;

import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class FinanceiroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;
	private PieChartModel pieModel2;

	@Inject
	private LancamentoDao lancamentoDao = new LancamentoDao();
	@Inject
	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lancamentoTotal;
	private double pagaveis;
	private double recebiveis;
	private double saldo;

	@PostConstruct
	public void init() {
		lancamentoTotal = new ArrayList<Lancamento>();
		lancamentoTotal = lancamentoDao.listaTodos();
		calcularTotais();
		createPieModel();
	}

	public List<Lancamento> getLancamentoTotal() {
		return lancamentoTotal;
	}

	public void setLancamentoTotal(List<Lancamento> lancamentoTotal) {
		this.lancamentoTotal = lancamentoTotal;
	}

	@Transacional
	public void calcularTotais() {

		for (int i = 0; i < lancamentoTotal.size(); i++) {
			if ((lancamentoTotal.get(i).getTipolacamento() == (TipoLancamento.Pagar))) {
				pagaveis = pagaveis + (lancamentoTotal.get(i).getValor() * -1);
			}
			if ((lancamentoTotal.get(i).getTipolacamento() == (TipoLancamento.Receber))) {
				recebiveis = recebiveis + (lancamentoTotal.get(i).getValor());
			}
			saldo = recebiveis - pagaveis;
		}
	}

	public double getPagaveis() {
		return pagaveis;
	}

	public void setPagaveis(double pagaveis) {
		this.pagaveis = pagaveis;
	}

	public double getRecebiveis() {
		return recebiveis;
	}

	public void setRecebiveis(double recebiveis) {
		this.recebiveis = recebiveis;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		pieModel.set("Receitas", recebiveis);
		pieModel.set("Despesas", pagaveis);

		pieModel.setTitle("Saúde Financeira");
		pieModel.setLegendPosition("w");
		pieModel.setShadow(false);

		pieModel2 = new PieChartModel();
		pieModel2.set("Saldo", saldo);
		pieModel2.set("Despesas", pagaveis);

		pieModel2.setTitle("Saldo Financeiro");
		pieModel2.setLegendPosition("w");
		pieModel2.setShadow(false);

	}

}
