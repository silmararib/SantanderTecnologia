package ib.mybank.api.domain.transacoes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    public Page<Transacao> findAllByData(LocalDate data, Pageable paginacao);
}
