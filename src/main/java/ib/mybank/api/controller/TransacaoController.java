package ib.mybank.api.controller;

import ib.mybank.api.domain.cliente.ClienteDados;
import ib.mybank.api.domain.transacoes.TransacaoDados;
import ib.mybank.api.domain.transacoes.TransacaoDadosOperacao;
import ib.mybank.api.domain.transacoes.TransacaoRepository;
import ib.mybank.api.domain.transacoes.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @Autowired
    private TransacaoRepository repository;

    @PostMapping("/saque")
    @Transactional
    public ResponseEntity sacar(@RequestBody @Valid TransacaoDadosOperacao dados) {
        var transacao = service.sacar(dados);

        return ResponseEntity.ok(new TransacaoDados(transacao));
    }

    @PostMapping("/deposito")
    @Transactional
    public ResponseEntity depositar(@RequestBody @Valid TransacaoDadosOperacao dados) {
        var transacao = service.depositar(dados);

        return ResponseEntity.ok(new TransacaoDados(transacao));
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoDados>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(TransacaoDados::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{data}")
    public ResponseEntity<Page<TransacaoDados>> listarPorData(@PathVariable LocalDate data, @PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAllByData(data, paginacao).map(TransacaoDados::new);

        return ResponseEntity.ok(page);
    }
}
