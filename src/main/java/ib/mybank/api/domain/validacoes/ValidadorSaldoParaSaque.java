package ib.mybank.api.domain.validacoes;

import ib.mybank.api.domain.calculos.CalculadorNovoSaldoSaque;
import ib.mybank.api.domain.calculos.CalculadorTaxa;
import ib.mybank.api.domain.cliente.ClienteRepository;
import ib.mybank.api.domain.transacoes.TransacaoDadosOperacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorSaldoParaSaque implements ValidadorSaque {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CalculadorTaxa calculadorTaxa;

    @Autowired
    private CalculadorNovoSaldoSaque calculadorSaque;
    @Override
    public void validar(TransacaoDadosOperacao dados) {
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var taxa = calculadorTaxa.calcularValorTaxa(dados.valor(), cliente.isPlanoExclusive());
        var novoSaldo = calculadorSaque.calcular(cliente.getSaldo(), dados.valor(), taxa);

        if (novoSaldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidacaoException("Não há saldo suficiente para realizar esta operação.");
        }
    }
}
