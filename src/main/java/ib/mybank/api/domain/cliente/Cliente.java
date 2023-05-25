package ib.mybank.api.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean planoExclusive;
    private BigDecimal saldo;
    private String numeroConta;
    private Date dataNascimento;

    public Cliente(ClienteDadosCadastro cliente) {
        this.nome = cliente.nome();
        this.planoExclusive = cliente.planoExclusive();
        this.saldo = cliente.saldo();
        this.numeroConta = cliente.numeroConta();;
        this.dataNascimento = cliente.dataNascimento();
    }

    public void atualizarInformacoes(ClienteDadosAtualizacao cliente) {
        if (cliente.planoExclusive() != null) {
            this.planoExclusive = cliente.planoExclusive();
        }

        if (cliente.saldo() != null) {
            this.saldo = cliente.saldo();
        }
    }

    public void atualizarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
