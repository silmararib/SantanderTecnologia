package ib.mybank.api.domain.validacoes;

import ib.mybank.api.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorExistenciaCliente {

    @Autowired
    private ClienteRepository clienteRepository;
    public void validar(Long idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new ValidacaoException("Não foi encontrado nenhum cliente com o ID informado.");
        }
    }
}
