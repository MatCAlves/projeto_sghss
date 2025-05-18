package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.Receita;

@Repository
@Transactional
public class ReceitaDAO implements CRUD<Receita, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Receita pesquisaPeloId(Long id) {
        return entityManager.find(Receita.class, id);
    }

    @Override
    public List<Receita> listaTodos() {
        Query query = entityManager.createQuery("select r from Receita r");
        return query.getResultList();
    }

    @Override
    public void insere(Receita receita) {
        entityManager.persist(receita);
    }

    @Override
    public void atualiza(Receita receita) {
        entityManager.merge(receita);
    }

    @Override
    public void remove(Receita receita) {
        entityManager.remove(receita);
    }
}