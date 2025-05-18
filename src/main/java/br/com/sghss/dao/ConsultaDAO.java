package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.Consulta;

@Repository
@Transactional
public class ConsultaDAO implements CRUD<Consulta, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Consulta pesquisaPeloId(Long id) {
        return entityManager.find(Consulta.class, id);
    }

    @Override
    public List<Consulta> listaTodos() {
        Query query = entityManager.createQuery("select c from Consulta c");
        return query.getResultList();
    }

    @Override
    public void insere(Consulta consulta) {
        entityManager.persist(consulta);
    }

    @Override
    public void atualiza(Consulta consulta) {
        entityManager.merge(consulta);
    }

    @Override
    public void remove(Consulta consulta) {
        entityManager.remove(consulta);
    }
}