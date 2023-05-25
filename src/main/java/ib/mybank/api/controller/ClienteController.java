package ib.mybank.api.controller;

import ib.mybank.api.domain.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ClienteDadosCadastro dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDados(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDados>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ClienteDados::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid ClienteDadosAtualizacao dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new ClienteDados(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);

        return ResponseEntity.ok(new ClienteDados(cliente));
    }
}
