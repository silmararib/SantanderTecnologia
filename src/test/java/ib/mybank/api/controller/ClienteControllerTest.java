package ib.mybank.api.controller;

import ib.mybank.api.domain.cliente.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ClienteDadosCadastro> clienteDadosCadastroJson;

    @Autowired
    private JacksonTester<ClienteDados> clienteDadosJson;

    @Autowired
    private JacksonTester<ClienteDadosAtualizacao> clienteDadosAtualizacaoJson;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deveria devolver o código 400 quando as informações não são válidas.")
    void cliente_cenario1() throws Exception {
        var response = mvc
                .perform(post("/clientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver o código 200 quando as informações são válidas.")
    void cliente_cenario2() throws Exception {
        var data_nascimento = new Date();
        var response = mvc
                .perform(
                        post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(clienteDadosCadastroJson.write(
                                        new ClienteDadosCadastro("Cliente1000", true, BigDecimal.ZERO, "123456-7", data_nascimento)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = clienteDadosJson.write(
                new ClienteDados(null, "Cliente1000", true, BigDecimal.ZERO, "123456-7", data_nascimento)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver o código 400 quando as informações não são válidas.")
    void cliente_cenario3() throws Exception {
        var response = mvc
                .perform(put("/clientes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}