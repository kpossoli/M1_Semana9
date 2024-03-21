package com.semana09.biblioteca.model;

import jakarta.persistence.*; // Importing Jakarta Persistence API annotations
import lombok.Data; // Importing Lombok @Data annotation for automatic generation of getters, setters, toString, etc.

@Entity // Marks the class as an entity, indicating it will be mapped to a database table
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class Visitante { // Declaring a class named Visitante

    @Id // Indicates the primary key field of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Integer id; // Declaring an Integer field named id to store the primary key

    @Column(nullable = false) // Specifies the mapping for a database column
    private String nome; // Declaring a String field to store the visitante's name

    @Column // Specifies the mapping for a database column
    private String telefone; // Declaring a String field to store the visitante's telefone

    public Visitante() { // Default constructor for the Visitante class
    }

    public Visitante(String nome, String telefone) { // Constructor with parameters for the Visitante class
        this.nome = nome; // Assigning the provided name to the instance variable nome
        this.telefone = telefone; // Assigning the provided telefone to the instance variable telefone
    }

    // Getter method for retrieving the id field
    public Integer getId() {
        return id;
    }

    // Getter method for retrieving the nome field
    public String getNome() {
        return nome;
    }

    // Setter method for setting the nome field
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter method for retrieving the telefone field
    public String getTelefone() {
        return telefone;
    }

    // Setter method for setting the telefone field
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

