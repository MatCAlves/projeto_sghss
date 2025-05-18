package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.SuprimentoEstoque;

@Repository
@Transactional
public class SuprimentoEstoqueDAO implements CRUD<SuprimentoEstoque, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SuprimentoEstoque pesquisaPeloId(Long id) {
        return entityManager.find(SuprimentoEstoque.class, id);
    }

    @Override
    public List<SuprimentoEstoque> listaTodos() {
        Query query = entityManager.createQuery("select se from SuprimentoEstoque se");
        return query.getResultList();
    }

    @Override
    public void insere(SuprimentoEstoque suprimentoEstoque) {
        entityManager.persist(suprimentoEstoque);
    }

    @Override
    public void atualiza(SuprimentoEstoque suprimentoEstoque) {
        entityManager.merge(suprimentoEstoque);
    }

    @Override
    public void remove(SuprimentoEstoque suprimentoEstoque) {
        entityManager.remove(suprimentoEstoque);
    }
}