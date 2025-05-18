package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.Suprimento;

@Repository
@Transactional
public class SuprimentoDAO implements CRUD<Suprimento, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Suprimento pesquisaPeloId(Long id) {
        return entityManager.find(Suprimento.class, id);
    }

    @Override
    public List<Suprimento> listaTodos() {
        Query query = entityManager.createQuery("select s from Suprimento s");
        return query.getResultList();
    }

    @Override
    public void insere(Suprimento suprimento) {
        entityManager.persist(suprimento);
    }

    @Override
    public void atualiza(Suprimento suprimento) {
        entityManager.merge(suprimento);
    }

    @Override
    public void remove(Suprimento suprimento) {
        entityManager.remove(suprimento);
    }
}