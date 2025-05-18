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

import br.com.sghss.model.Paciente;
import br.com.sghss.bo.PacienteBO;

@RestController
public class PacienteRestController {
    @Autowired
    private PacienteBO pacienteBO;

    @RequestMapping(value = "/api/pacientes", method = RequestMethod.GET)
    public List<Paciente> listaTodos() {
        return pacienteBO.listaTodos();
    }

    @RequestMapping(value = "/api/pacientes/{id}", method = RequestMethod.GET)
    public Paciente pesquisaPeloId(@PathVariable Long id) {
        return pacienteBO.pesquisaPeloId(id);
    }

    @RequestMapping(value = "/api/pacientes", method = RequestMethod.POST)
    public Paciente insere(@RequestBody Paciente paciente) {
        pacienteBO.insere(paciente);
        return paciente;
    }

    @RequestMapping(value = "/api/pacientes/{id}", method = RequestMethod.PUT)
    public Paciente atualiza(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        pacienteBO.atualiza(paciente);
        return paciente;
    }

    @RequestMapping(value = "/api/pacientes/{id}", method = RequestMethod.DELETE)
    public Paciente remove(@PathVariable Long id) {
        Paciente paciente = pacienteBO.pesquisaPeloId(id);
        pacienteBO.remove(paciente);
        return paciente;
    }
}