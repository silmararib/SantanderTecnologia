package ib.mybank.api.domain.validacoes;

import ib.mybank.api.domain.transacoes.TransacaoDadosOperacao;

public interface ValidadorSaque {
    void validar(TransacaoDadosOperacao dados);
}
