package br.com.sistema.comercio.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private BigDecimal preco;
    private String categoria;

    // getters and setters...
}
