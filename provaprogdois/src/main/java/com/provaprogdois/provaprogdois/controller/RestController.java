package com.provaprogdois.provaprogdois.controller;

import com.provaprogdois.provaprogdois.model.EnderecoModel;
import com.provaprogdois.provaprogdois.model.PessoaModel;
import com.provaprogdois.provaprogdois.repository.EnderecoRepository;
import com.provaprogdois.provaprogdois.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    //Puxa todos os usuarios
    @GetMapping("/pessoas")
    public ResponseEntity consultar() {
        return ResponseEntity.ok().body(pessoaRepository.findAll());
    }

    //Puxa um usuario especifico
    @GetMapping(path = "/pessoa/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer codigo) {
        return pessoaRepository.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/pessoa/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer codigo, @RequestBody PessoaModel pessoa) {
        return pessoaRepository.findById(codigo)
                .map(record -> {
                    record.setNome(pessoa.getNome());
                    record.setSobrenome(pessoa.getSobrenome());
                    record.setCpf(pessoa.getCpf());
                    record.setRg(pessoa.getRg());
                    PessoaModel updated = pessoaRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //Instancia uma pessoa
    @PostMapping("/pessoa")
    public ResponseEntity cadastrar(@RequestBody PessoaModel pessoa) {
        return ResponseEntity.ok().body(pessoaRepository.save(pessoa));
    }

    //Delete uma pessoa
    @DeleteMapping(path = "/pessoa/{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer codigo) {
        return pessoaRepository.findById(codigo)
                .map(record -> {
                    pessoaRepository.deleteById(codigo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    //CRUD DOS ENDERECOS

    //Deleta o endereco
    @DeleteMapping(path = "/endereco/{id}")
    public ResponseEntity deletarEndereco(@PathVariable("id") Integer codigo) {
        return enderecoRepository.findById(codigo)
                .map(record -> {
                    enderecoRepository.deleteById(codigo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    //Cria um endereco para um usuario
    @PostMapping("/pessoa/{id}/endereco")
    public ResponseEntity cadastrarEndereco(@PathVariable("id") Integer codigo, @RequestBody EnderecoModel endereco) {
        endereco.setPessoa(pessoaRepository.findById(codigo).get());
        return ResponseEntity.ok().body(enderecoRepository.save(endereco));
    }

    //Edita um endereco
    @PutMapping(path = "/endereco/{id}")
    public ResponseEntity atualizarEndereco(@PathVariable("id") Integer codigo, @RequestBody EnderecoModel endereco) {
        return enderecoRepository.findById(codigo)
                .map(record -> {
                    record.setCep(endereco.getCep());
                    record.setLogradouro(endereco.getLogradouro());
                    record.setNumero(endereco.getNumero());
                    record.setComplemento(endereco.getComplemento());
                    EnderecoModel updated = enderecoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Opa!";
    }

}
