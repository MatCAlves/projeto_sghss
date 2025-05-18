package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.SuprimentoDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.Suprimento;

@Service
public class SuprimentoBO implements CRUD<Suprimento, Long> {
    @Autowired
    private SuprimentoDAO dao;

    @Override
    public Suprimento pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Suprimento> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Suprimento suprimento) {
        dao.insere(suprimento);
    }

    @Override
    public void atualiza(Suprimento suprimento) {
        dao.atualiza(suprimento);
    }

    @Override
    public void remove(Suprimento suprimento) {
        dao.remove(suprimento);
    }

    public void inativa(Suprimento suprimento) {
        suprimento.setAtivo(false);
        dao.atualiza(suprimento);
    }

    public void ativa(Suprimento suprimento) {
        suprimento.setAtivo(true);
        dao.atualiza(suprimento);
    }
}