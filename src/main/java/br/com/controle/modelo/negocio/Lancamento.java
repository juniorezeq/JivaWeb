package br.com.controle.modelo.negocio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tb_lancamentos")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contapagar")
	private Long id;
	@Column(name = "descricao", length = 80, nullable = false)
	private String descricao;
	@Column(name = "valor",  nullable = false)
	private double valor;
	@Column(name = "vencimento", insertable = true, updatable = true)
	private LocalDate vencimento;
	@Column (name = "tipolancamento")
	private TipoLancamento tipolacamento;
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return  descricao + " Valor: " + valor;
	}

	public double getValor() {
		return valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoLancamento getTipolacamento() {
		return tipolacamento;
	}

	public void setTipolacamento(TipoLancamento tipolacamento) {
		this.tipolacamento = tipolacamento;
	}
	
	

}