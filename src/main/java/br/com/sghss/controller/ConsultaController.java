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

import br.com.sghss.bo.ConsultaBO;
import br.com.sghss.bo.ProfissionalBO;
import br.com.sghss.bo.PacienteBO;
import br.com.sghss.model.Consulta;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaBO consultaBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @Autowired
    private PacienteBO pacienteBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        Long profissionalId = null;
        Long pacienteId = null;
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("profissionais", profissionalBO.listaTodos());
        model.addAttribute("pacientes", pacienteBO.listaTodos());
        return new ModelAndView("consulta/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Consulta consulta, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("profissionais", profissionalBO.listaTodos());
            model.addAttribute("pacientes", pacienteBO.listaTodos());
            return "consulta/formulario";
        }

        if (consulta.getId() == null) {
            consultaBO.insere(consulta);
            attr.addFlashAttribute("feedback", "Consulta cadastrada com sucesso.");
        } else {
            consultaBO.atualiza(consulta);
            attr.addFlashAttribute("feedback", "Consulta atualizada com sucesso.");
        }
        return "redirect:/consultas";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("consultas", consultaBO.listaTodos());
        return new ModelAndView("consulta/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("consulta", consultaBO.pesquisaPeloId(id));
            model.addAttribute("profissionais", profissionalBO.listaTodos());
            model.addAttribute("pacientes", pacienteBO.listaTodos());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("consulta/formulario", model);
    }

    @RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		Consulta consulta = consultaBO.pesquisaPeloId(id);
		consultaBO.remove(consulta);
		attr.addFlashAttribute("feedback", "Consulta cancelada com sucesso.");
		return "redirect:/consultas";
    }
}