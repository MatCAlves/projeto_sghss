package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.ReceitaDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.Receita;

@Service
public class ReceitaBO implements CRUD<Receita, Long> {
    @Autowired
    private ReceitaDAO dao;

    @Override
    public Receita pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Receita> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Receita receita) {
        dao.insere(receita);
    }

    @Override
    public void atualiza(Receita receita) {
        dao.atualiza(receita);
    }

    @Override
    public void remove(Receita receita) {
        dao.remove(receita);
    }
}