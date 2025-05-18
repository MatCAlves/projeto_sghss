package br.com.sghss.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import br.com.sghss.model.SuprimentoEstoque;
import br.com.sghss.bo.SuprimentoEstoqueBO;
import br.com.sghss.bo.SuprimentoBO;
import br.com.sghss.model.Suprimento;

@RestController
public class SuprimentoEstoqueRestController {
    @Autowired
    private SuprimentoEstoqueBO suprimentoEstoqueBO;

    @Autowired
    private SuprimentoBO suprimentoBO;

    @RequestMapping(value = "/api/suprimentos", method = RequestMethod.GET)
    public List<SuprimentoEstoque> listaTodos() {
        return suprimentoEstoqueBO.listaTodos();
    }

    @RequestMapping(value = "/api/suprimentos/{id}", method = RequestMethod.GET)
    public SuprimentoEstoque pesquisaPeloId(@PathVariable Long id) {
        return suprimentoEstoqueBO.pesquisaPeloId(id);
    }

    @RequestMapping(value = "/api/suprimentos", method = RequestMethod.POST)
    public SuprimentoEstoque insere(@RequestBody SuprimentoEstoque suprimentoEstoque) {
        Suprimento suprimento = suprimentoBO.pesquisaPeloId(suprimentoEstoque.getSuprimento().getId());
        suprimentoEstoque.setSuprimento(suprimento);
        suprimentoEstoqueBO.insere(suprimentoEstoque);
        return suprimentoEstoque;
    }

    @RequestMapping(value = "/api/suprimentos/{id}", method = RequestMethod.PUT)
    public SuprimentoEstoque atualiza(@PathVariable Long id, @RequestBody SuprimentoEstoque suprimentoEstoque) {
        suprimentoEstoque.setId(id);
        suprimentoEstoque.setSuprimento(suprimentoBO.pesquisaPeloId(suprimentoEstoque.getSuprimento().getId()));
        suprimentoEstoqueBO.atualiza(suprimentoEstoque);
        return suprimentoEstoque;
    }

    @RequestMapping(value = "/api/suprimentos/{id}", method = RequestMethod.DELETE)
    public SuprimentoEstoque remove(@PathVariable Long id) {
        SuprimentoEstoque suprimentoEstoque = suprimentoEstoqueBO.pesquisaPeloId(id);
        suprimentoEstoqueBO.remove(suprimentoEstoque);
        return suprimentoEstoque;
    }
}