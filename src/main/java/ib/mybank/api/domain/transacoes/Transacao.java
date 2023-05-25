package ib.mybank.api.domain.transacoes;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "transacoes")
@Entity(name = "Transacao")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_cliente;
    private Operacao operacao;
    private BigDecimal valor;
    private BigDecimal taxaAplicada;
    private BigDecimal saldoAnterior;
    private BigDecimal novoSaldo;
    private LocalDate data;
    public Transacao(Long id_cliente, Operacao operacao, BigDecimal valor, BigDecimal taxaAplicada, BigDecimal saldoAnterior, BigDecimal novoSaldo) {
        this.id_cliente = id_cliente;
        this.operacao = operacao;
        this.valor = valor;
        this.taxaAplicada = taxaAplicada;
        this.saldoAnterior = saldoAnterior;
        this.novoSaldo = novoSaldo;
        this.data = LocalDate.now();
    }
}
