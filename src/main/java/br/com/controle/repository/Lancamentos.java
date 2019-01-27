package br.com.controle.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.controle.modelo.negocio.Lancamento;


public class Lancamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager entityManager;
    
    public Lancamento salvar(Lancamento lancamento) {
        return entityManager.merge(lancamento);
    }
    
    public void remover(Lancamento lancamento) {
        entityManager.remove(entityManager.getReference(Lancamento.class, lancamento.getId()));
    }
    
    public Lancamento buscar(Long id) {
        return entityManager.find(Lancamento.class, id);
    }
    
    public List<Lancamento> todos() {
        return entityManager.createQuery("from tb_lancamento", Lancamento.class).getResultList();
    }

    public List<Lancamento> pesquisar(String termo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Lancamento> criteriaQuery = criteriaBuilder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("nome"), termo + "%"));
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}