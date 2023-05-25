package ib.mybank.api.domain.calculos;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculadorTaxa {

    public BigDecimal calcularValorTaxa(BigDecimal valor, boolean planoExclusive) {
        var taxa = 0.0;
        
        if (!planoExclusive && valor.compareTo(new BigDecimal(100)) > 0) {
            if (valor.compareTo(new BigDecimal(300)) <= 0) {
                taxa = 0.4/100;
            } else {
                taxa = 1.0/100;
            }
        }
        
        return valor.multiply(new BigDecimal(taxa)).setScale(2, RoundingMode.HALF_UP);
    }
}
