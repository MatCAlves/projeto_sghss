package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.Profissional;

@Repository
@Transactional
public class ProfissionalDAO implements CRUD<Profissional, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profissional pesquisaPeloId(Long id) {
        return entityManager.find(Profissional.class, id);
    }

    @Override
    public List<Profissional> listaTodos() {
        Query query = entityManager.createQuery("select p from Profissional p");
        return query.getResultList();
    }

    @Override
    public void insere(Profissional profissional) {
        entityManager.persist(profissional);
    }

    @Override
    public void atualiza(Profissional profissional) {
        entityManager.merge(profissional);
    }

    @Override
    public void remove(Profissional profissional) {
        entityManager.remove(profissional);
    }
}