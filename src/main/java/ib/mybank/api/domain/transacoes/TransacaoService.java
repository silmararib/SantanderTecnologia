package ib.mybank.api.domain.transacoes;

import ib.mybank.api.domain.calculos.*;
import ib.mybank.api.domain.validacoes.*;
import ib.mybank.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CalculadorNovoSaldoSaque calculadorSaque;

    @Autowired
    private CalculadorNovoSaldoDeposito calculadorDeposito;

    @Autowired
    private CalculadorTaxa calculadorTaxa;

    @Autowired
    private ValidadorExistenciaCliente validadorCliente;

    @Autowired
    private List<ValidadorSaque> validadores;
    public Transacao sacar(TransacaoDadosOperacao dados) {
        validadorCliente.validar(dados.idCliente());
        validadores.forEach((v -> v.validar(dados)));

        var cliente = clienteRepository.getReferenceById(dados.idCliente());

        var taxa = calculadorTaxa.calcularValorTaxa(dados.valor(), cliente.isPlanoExclusive());
        var novoSaldo = calculadorSaque.calcular(cliente.getSaldo(), dados.valor(), taxa);

        var transacao = new Transacao(cliente.getId(), Operacao.SAQUE, dados.valor(), taxa, cliente.getSaldo(), novoSaldo);
        transacaoRepository.save(transacao);

        cliente.atualizarSaldo(novoSaldo);
        clienteRepository.save(cliente);

        return transacao;
    }

    public Transacao depositar(TransacaoDadosOperacao dados) {
        validadorCliente.validar(dados.idCliente());

        var cliente = clienteRepository.getReferenceById(dados.idCliente());

        var novoSaldo = calculadorDeposito.calcular(cliente.getSaldo(), dados.valor());

        var transacao = new Transacao(cliente.getId(), Operacao.DEPOSITO, dados.valor(), BigDecimal.ZERO.setScale(2), cliente.getSaldo(), novoSaldo);
        transacaoRepository.save(transacao);

        cliente.atualizarSaldo(novoSaldo);
        clienteRepository.save(cliente);

        return transacao;
    }
}
