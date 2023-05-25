package ib.mybank.api.domain.transacoes;

import com.fasterxml.jackson.annotation.JsonFormat;
import ib.mybank.api.domain.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public record TransacaoDados(
        Long id,
        Long id_cliente,
        Operacao operacao,
        BigDecimal valor,
        BigDecimal taxaAplicada,
        BigDecimal saldoAnterior,
        BigDecimal novoSaldo,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data
) {
        public TransacaoDados(Transacao transacao) {
                this(
                        transacao.getId(),
                        transacao.getId_cliente(),
                        transacao.getOperacao(),
                        transacao.getValor(),
                        transacao.getTaxaAplicada(),
                        transacao.getSaldoAnterior(),
                        transacao.getNovoSaldo(),
                        transacao.getData()
                );
        }
}
