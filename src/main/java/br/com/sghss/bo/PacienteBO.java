package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.PacienteDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.Paciente;
import br.com.sghss.model.Consulta;

@Service
public class PacienteBO implements CRUD<Paciente, Long> {
    @Autowired
    private PacienteDAO dao;

    @Override
    public Paciente pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Paciente> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Paciente paciente) {
        dao.insere(paciente);
    }

    @Override
    public void atualiza(Paciente paciente) {
        dao.atualiza(paciente);
    }

    @Override
    public void remove(Paciente paciente) {
        dao.remove(paciente);
    }

    public void inativa(Paciente paciente) {
        paciente.setAtivo(false);
        dao.atualiza(paciente);
    }

    public void ativa(Paciente paciente) {
        paciente.setAtivo(true);
        dao.atualiza(paciente);
    }

    public List<Consulta> listaConsultas(Paciente paciente) {
        return dao.listaConsultas(paciente);
    }
}