package ib.mybank.api.domain.cliente;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ClienteDadosAtualizacao(
        @NotNull
        Long id,
        @JsonAlias("plano_exclusive")
        Boolean planoExclusive,
        @PositiveOrZero(message = "O saldo da conta não pode ser negativo.")
        @Digits(integer = 10, fraction = 2, message="Valores monetários devem possuir duas casas decimais.")
        BigDecimal saldo
) {
}
