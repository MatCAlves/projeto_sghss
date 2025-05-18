package br.com.sghss.controller;

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

import br.com.sghss.bo.ProfissionalBO;
import br.com.sghss.model.Profissional;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
    @Autowired
    private ProfissionalBO profissionalBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("profissional", new Profissional());
        return new ModelAndView("profissional/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Profissional profissional, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) return "profissional/formulario";

        if (profissional.getId() == null) {
            profissionalBO.insere(profissional);
            attr.addFlashAttribute("feedback", "Profissional cadastrado com sucesso.");
        } else {
            profissionalBO.atualiza(profissional);
            attr.addFlashAttribute("feedback", "Profissional atualizado com sucesso.");
        }
        return "redirect:/profissionais";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("profissionais", profissionalBO.listaTodos());
        return new ModelAndView("profissional/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("profissional", profissionalBO.pesquisaPeloId(id));
        return new ModelAndView("profissional/formulario", model);
    }

    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Profissional profissional = profissionalBO.pesquisaPeloId(id);
            profissionalBO.inativa(profissional);
            attr.addFlashAttribute("feedback", "Profissional inativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/profissionais";
    }

    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Profissional profissional = profissionalBO.pesquisaPeloId(id);
            profissionalBO.ativa(profissional);
            attr.addFlashAttribute("feedback", "Profissional ativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/profissionais";
    }
}