package br.com.sghss.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import br.com.sghss.bo.SuprimentoBO;
import br.com.sghss.model.Suprimento;
import br.com.sghss.model.Categoria;

@Controller
@RequestMapping("/suprimentos")
public class SuprimentoController {
    @Autowired
    private SuprimentoBO suprimentoBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("suprimento", new Suprimento());
        model.addAttribute("categorias", Arrays.asList(Categoria.values()));
        return new ModelAndView("suprimento/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Suprimento suprimento, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", Arrays.asList(Categoria.values()));
            return "suprimento/formulario";
        }

        if (suprimento.getId() == null) {
            suprimentoBO.insere(suprimento);
            attr.addFlashAttribute("feedback", "Suprimento cadastrado com sucesso.");
        } else {
            suprimentoBO.atualiza(suprimento);
            attr.addFlashAttribute("feedback", "Suprimento atualizado com sucesso.");
        }
        return "redirect:/suprimentos";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("suprimentos", suprimentoBO.listaTodos());
        return new ModelAndView("suprimento/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("suprimento", suprimentoBO.pesquisaPeloId(id));
            model.addAttribute("categorias", Arrays.asList(Categoria.values()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("suprimento/formulario", model);
    }

    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Suprimento suprimento = suprimentoBO.pesquisaPeloId(id);
            suprimentoBO.inativa(suprimento);
            attr.addFlashAttribute("feedback", "Suprimento inativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/suprimentos";
    }

    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Suprimento suprimento = suprimentoBO.pesquisaPeloId(id);
            suprimentoBO.ativa(suprimento);
            attr.addFlashAttribute("feedback", "Suprimento ativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/suprimentos";
    }
}