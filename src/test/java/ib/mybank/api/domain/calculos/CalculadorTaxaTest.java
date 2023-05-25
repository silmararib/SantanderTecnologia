package ib.mybank.api.domain.calculos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class CalculadorTaxaTest {

    @Autowired
    private CalculadorTaxa calculador;
    @Test
    @DisplayName("Para valores menores que 100.00 não deve haver taxa de administração.")
    void taxa_cenario1() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(90.00), false).compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    @DisplayName("Para valores iguais a 100.00 não deve haver taxa de administração.")
    void taxa_cenario2() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(100.00), false).compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    @DisplayName("Para valores maiores que 100.00 e menores que 300.00 a taxa de administração deve ser de 0.4%.")
    void taxa_cenario3() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(200.00), false).compareTo(new BigDecimal(0.80).setScale(2, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    @DisplayName("Para valores iguais a 300.00 a taxa de administração deve ser de 0.4%.")
    void taxa_cenario4() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(300.00), false).compareTo(new BigDecimal(1.20).setScale(2, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    @DisplayName("Para valores maiores que 300.00 a taxa de administração deve ser de 1%.")
    void taxa_cenario5() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(400.00), false).compareTo(new BigDecimal(4.00).setScale(2, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    @DisplayName("Para clientes com plano Exclusive não deve haver taxa de administração.")
    void taxa_cenario6() throws Exception {
        assertThat(calculador.calcularValorTaxa(new BigDecimal(400.00), true).compareTo(BigDecimal.ZERO) == 0);
    }
}