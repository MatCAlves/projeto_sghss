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

import br.com.sghss.bo.ReceitaBO;
import br.com.sghss.bo.ProfissionalBO;
import br.com.sghss.bo.PacienteBO;
import br.com.sghss.model.Receita;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaBO receitaBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @Autowired
    private PacienteBO pacienteBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        Long profissionalId = null;
        Long pacienteId = null;
        model.addAttribute("receita", new Receita());
        model.addAttribute("profissionais", profissionalBO.listaTodos());
        model.addAttribute("pacientes", pacienteBO.listaTodos());
        return new ModelAndView("receita/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Receita receita, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (receita.getPaciente().getId() == null) {
            result.rejectValue("paciente", "field.required");
        }
        
        if (result.hasErrors()) {
            model.addAttribute("profissionais", profissionalBO.listaTodos());
            model.addAttribute("pacientes", pacienteBO.listaTodos());
            return "receita/formulario";
        }

        if (receita.getId() == null) {
            receitaBO.insere(receita);
            attr.addFlashAttribute("feedback", "Receita cadastrada com sucesso.");
        } else {
            receitaBO.atualiza(receita);
            attr.addFlashAttribute("feedback", "Receita atualizada com sucesso.");
        }
        return "redirect:/receitas";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("receitas", receitaBO.listaTodos());
        return new ModelAndView("receita/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("receita", receitaBO.pesquisaPeloId(id));
            model.addAttribute("profissionais", profissionalBO.listaTodos());
            model.addAttribute("pacientes", pacienteBO.listaTodos());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("receita/formulario", model);
    }

    @RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		Receita receita = receitaBO.pesquisaPeloId(id);
		receitaBO.remove(receita);
		attr.addFlashAttribute("feedback", "Receita removida com sucesso.");
		return "redirect:/receitas";
    }
}