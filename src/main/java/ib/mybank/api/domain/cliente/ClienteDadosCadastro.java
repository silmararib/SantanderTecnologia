package ib.mybank.api.domain.cliente;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.util.Date;

public record ClienteDadosCadastro(
        @NotBlank(message = "O nome do cliente precisa ser informado.")
        @Size(max = 100, message = "O nome do cliente é muito longo, tente abreviar o sobrenome.")
        String nome,
        @NotNull(message = "É necessário informar se o cliente possui o plano Exclusive.")
        @JsonAlias("plano_exclusive")
        boolean planoExclusive,
        @PositiveOrZero(message = "O saldo da conta não pode ser negativo.")
        @Digits(integer = 10, fraction = 2, message="Valores monetários devem possuir duas casas decimais.")
        BigDecimal saldo,
        @NotBlank(message = "É necessário informar o número da conta do cliente.")
        @Size(max = 15)
        @JsonAlias("numero_conta")
        String numeroConta,
        @NotNull(message = "É necessário informar a data de nascimento do cliente.")
        @Past
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonAlias("data_nascimento")
        Date dataNascimento
) {
}
