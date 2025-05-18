package br.com.sghss.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sghss.dao.SuprimentoEstoqueDAO;
import br.com.sghss.dao.CRUD;
import br.com.sghss.model.SuprimentoEstoque;

@Service
public class SuprimentoEstoqueBO implements CRUD<SuprimentoEstoque, Long> {
    @Autowired
    private SuprimentoEstoqueDAO dao;

    @Override
    public SuprimentoEstoque pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    @Override
    public List<SuprimentoEstoque> listaTodos() {
        return dao.listaTodos();
    }

    @Override
    public void insere(SuprimentoEstoque suprimentoEstoque) {
        dao.insere(suprimentoEstoque);
    }

    @Override
    public void atualiza(SuprimentoEstoque suprimentoEstoque) {
        dao.atualiza(suprimentoEstoque);
    }

    @Override
    public void remove(SuprimentoEstoque suprimentoEstoque) {
        dao.remove(suprimentoEstoque);
    }
}