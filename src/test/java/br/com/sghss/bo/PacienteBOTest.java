package br.com.sghss.bo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

import br.com.sghss.model.Paciente;
import br.com.sghss.model.Sexo;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class PacienteBOTest {
    @Autowired
    private PacienteBO bo;

    @Test
    @Order(1)
    public void insere() {
        Paciente paciente = new Paciente();
        paciente.setNome("José da Silva");
        paciente.setCpf("01234567890");
        paciente.setDataDeNascimento(LocalDate.of(2000, 1, 8));
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setTelefone("01234567890");
        paciente.setAtivo(true);
        paciente.setEmail("josedasilva@email.com");
        bo.insere(paciente);
    }

    @Test
    @Order(2)
    public void pesquisaPeloId() {
        Paciente paciente = bo.pesquisaPeloId(1L);
        System.out.println(paciente);
    }

    @Test
    @Order(3)
    public void atualiza() {
        Paciente paciente = bo.pesquisaPeloId(1L);
        paciente.setCpf("09876543210");
        bo.atualiza(paciente);
    }

    @Test
    @Order(4)
    public void inativa() {
        Paciente paciente = bo.pesquisaPeloId(1L);
        bo.inativa(paciente);
    }

    @Test
    @Order(5)
    public void ativa() {
        Paciente paciente = bo.pesquisaPeloId(1L);
        bo.ativa(paciente);
    }
}