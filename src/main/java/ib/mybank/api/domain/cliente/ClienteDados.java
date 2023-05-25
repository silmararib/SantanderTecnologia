package ib.mybank.api.domain.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record ClienteDados(
        Long id,
        String nome,
        boolean planoExclusive,
        BigDecimal saldo,
        String numeroConta,
        @JsonFormat(pattern = "dd/MM/yyyy")
        Date dataNascimento
) {
    public ClienteDados(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.isPlanoExclusive(),
                cliente.getSaldo(),
                cliente.getNumeroConta(),
                cliente.getDataNascimento()
        );
    }
}
