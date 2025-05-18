package br.com.sghss.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sghss.model.Paciente;
import br.com.sghss.model.Consulta;

@Repository
@Transactional
public class PacienteDAO implements CRUD<Paciente, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Paciente pesquisaPeloId(Long id) {
        return entityManager.find(Paciente.class, id);
    }

    @Override
    public List<Paciente> listaTodos() {
        Query query = entityManager.createQuery("select p from Paciente p");
        return (List<Paciente>) query.getResultList();
    }

    @Override
    public void insere(Paciente paciente) {
        entityManager.persist(paciente);
    }

    @Override
    public void atualiza(Paciente paciente) {
        entityManager.merge(paciente);
    }

    @Override
    public void remove(Paciente paciente) {
        entityManager.remove(paciente);
    }

    public List<Consulta> listaConsultas(Paciente paciente) {
        Long pacienteId = paciente.getId();
        Query query = entityManager.createQuery("from Consulta c where c.paciente.id = :pacienteId").setParameter("pacienteId", pacienteId);
        return (List<Consulta>) query.getResultList();
    }
}