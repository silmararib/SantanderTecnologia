package ib.mybank.api.domain.transacoes;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransacaoDadosOperacao(
        @NotNull(message = "É necessário informar o ID do cliente.")
        @JsonAlias("id_cliente")
        Long idCliente,
        @Positive(message = "É necessário informar o valor da operação, e esse valor deve maior que zero.")
        @Digits(integer = 10, fraction = 2, message="Valores monetários devem possuir duas casas decimais.")
        BigDecimal valor
) {
}
