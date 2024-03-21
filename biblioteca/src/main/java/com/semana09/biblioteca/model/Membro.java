package com.semana09.biblioteca.model;
import jakarta.persistence.*; // Importing Jakarta Persistence API annotations

import lombok.Data; // Importing Lombok @Data annotation for automatic generation of getters, setters, toString, etc.

@Entity // Marks the class as an entity, indicating it will be mapped to a database table
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class Membro { // Declaring a class named Membro

    @Id // Indicates the primary key field of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Integer id; // Declaring an Integer field named id to store the primary key

    @Column(nullable = false) // Specifies the mapping for a database column
    private String nome; // Declaring a String field named nome to store the name of the member

    @Column // Specifies the mapping for a database column
    private String endereco; // Declaring a String field named endereco to store the address of the member

    @Column// Specifies the mapping for a database column
    private String telefone; // Declaring a String field named telefone to store the phone number of the member

    public Membro() { // Default constructor for the Membro class
    }

    public Membro(String nome, String endereco, String telefone) { // Constructor with parameters for the Membro class
        this.nome = nome; // Assigning the provided name to the instance variable nome
        this.endereco = endereco; // Assigning the provided address to the instance variable endereco
        this.telefone = telefone; // Assigning the provided phone number to the instance variable telefone
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

    // Getter method for retrieving the endereco field
    public String getEndereco() {
        return endereco;
    }

    // Setter method for setting the endereco field
    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

