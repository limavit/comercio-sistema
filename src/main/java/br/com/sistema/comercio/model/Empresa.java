package br.com.sistema.comercio.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String cnpj;
    private Boolean ativo = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Construtor padrão
    public Empresa() {
    }

    // Construtor com todos os atributos
    public Empresa(Long id, String nome, String cnpj, Boolean ativo, LocalDateTime createdAt) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ativo = ativo;
        this.createdAt = createdAt;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
