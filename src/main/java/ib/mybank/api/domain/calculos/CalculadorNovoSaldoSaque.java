package ib.mybank.api.domain.calculos;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculadorNovoSaldoSaque {

    public BigDecimal calcular(BigDecimal saldoAtual, BigDecimal valorOperacao, BigDecimal taxa) {
        return saldoAtual.subtract(valorOperacao).subtract(taxa).setScale(2);
    }
}
