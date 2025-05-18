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

import br.com.sghss.bo.PacienteBO;
import br.com.sghss.bo.ConsultaBO;
import br.com.sghss.model.Paciente;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteBO pacienteBO;

    @Autowired
    private ConsultaBO consultaBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("paciente", new Paciente());
        return new ModelAndView("paciente/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Paciente paciente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) return "paciente/formulario";

        if (paciente.getId() == null) {
            pacienteBO.insere(paciente);
            attr.addFlashAttribute("feedback", "Cliente cadastrado com sucesso.");
        } else {
            pacienteBO.atualiza(paciente);
            attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso.");
        }
        return "redirect:/pacientes";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("pacientes", pacienteBO.listaTodos());
        return new ModelAndView("paciente/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", pacienteBO.pesquisaPeloId(id));
        return new ModelAndView("paciente/formulario", model);
    }

    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Paciente paciente = pacienteBO.pesquisaPeloId(id);
            pacienteBO.inativa(paciente);
            attr.addFlashAttribute("feedback", "Cliente inativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/pacientes";
    }

    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            Paciente paciente = pacienteBO.pesquisaPeloId(id);
            pacienteBO.ativa(paciente);
            attr.addFlashAttribute("feedback", "Cliente ativado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/pacientes";
    }

    @RequestMapping(value = "/historico/{id}", method = RequestMethod.GET)
    public ModelAndView historico(@PathVariable("id") Long id, ModelMap model) {
        Paciente paciente = pacienteBO.pesquisaPeloId(id);
        model.addAttribute("consultas", pacienteBO.listaConsultas(paciente));
        return new ModelAndView("paciente/historico", model);
    }
}