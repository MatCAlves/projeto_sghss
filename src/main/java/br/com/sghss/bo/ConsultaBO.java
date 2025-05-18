package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.ConsultaDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.Consulta;

@Service
public class ConsultaBO implements CRUD<Consulta, Long> {
    @Autowired
    private ConsultaDAO dao;

    @Override
    public Consulta pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Consulta> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Consulta consulta) {
        dao.insere(consulta);
    }

    @Override
    public void atualiza(Consulta consulta) {
        dao.atualiza(consulta);
    }

    @Override
    public void remove(Consulta consulta) {
        dao.remove(consulta);
    }
}