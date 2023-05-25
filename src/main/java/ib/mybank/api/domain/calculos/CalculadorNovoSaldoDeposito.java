package ib.mybank.api.domain.calculos;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculadorNovoSaldoDeposito {

    public BigDecimal calcular(BigDecimal saldoAtual, BigDecimal valorOperacao) {
        return saldoAtual.add(valorOperacao).setScale(2);
    }
}
