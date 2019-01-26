package br.com.controle.modelo.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.controle.modelo.negocio.Lancamento;

@Named
@RequestScoped
public class LancamentoDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Lancamento> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Lancamento>(this.em, Lancamento.class);
	}

	@Inject
	private EntityManager em;

	public boolean existeDescricao(String descricao) {
		TypedQuery<Lancamento> query = em
				.createQuery(" select u from Lancamento u " + " where u.descricao = :pdescricao", Lancamento.class);

		query.setParameter("pDescricao", descricao);
		try {
			@SuppressWarnings("unused")
			Lancamento resultado = query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

	public void adiciona(Lancamento conta) {
		dao.adiciona(conta);
	}

	public void atualiza(Lancamento conta) {
		em.merge(conta);
	}

	public void remove(Lancamento conta) {
		dao.remove(conta);
	}

	public Lancamento buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Lancamento> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<Lancamento> listaTodos() {
		return dao.listaTodos();
	}

	public Lancamento buscaPorDescricao(String descricao) {
		TypedQuery<Lancamento> query = em
				.createQuery(" select u from Lancamento u " + " where u.descricao = :pDescricao", Lancamento.class);

		query.setParameter("pDescricao", descricao);
		try {
			Lancamento resultado = query.getSingleResult();
			return resultado;
		} catch (NoResultException ex) {
			return null;
		}
	}

}
