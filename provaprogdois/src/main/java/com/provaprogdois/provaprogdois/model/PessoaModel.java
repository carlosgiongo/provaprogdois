package com.provaprogdois.provaprogdois.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pessoa")
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public String nome;

    @Column
    public String sobrenome;

    @Column
    public String cpf;

    @Column
    public String rg;

    @OneToMany(mappedBy = "pessoa")
    @JsonManagedReference
    private Set<EnderecoModel> enderecos;

    //GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Set<EnderecoModel> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }
}
