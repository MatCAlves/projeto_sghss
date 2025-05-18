package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.ProfissionalDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.Profissional;

@Service
public class ProfissionalBO implements CRUD<Profissional, Long> {
    @Autowired
    private ProfissionalDAO dao;

    @Override
    public Profissional pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<Profissional> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(Profissional profissional) {
        dao.insere(profissional);
    }

    @Override
    public void atualiza(Profissional profissional) {
        dao.atualiza(profissional);
    }

    @Override
    public void remove(Profissional profissional) {
        dao.remove(profissional);
    }

    public void inativa(Profissional profissional) {
        profissional.setAtivo(false);
        dao.atualiza(profissional);
    }

    public void ativa(Profissional profissional) {
        profissional.setAtivo(true);
        dao.atualiza(profissional);
    }
}