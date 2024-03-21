package com.semana09.biblioteca.model;

import jakarta.persistence.*; // Importing Jakarta Persistence API annotations
import lombok.Data; // Importing Lombok @Data annotation for automatic generation of getters, setters, toString, etc.

@Entity // Marks the class as an entity, indicating it will be mapped to a database table
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class Bibliotecario { // Declaring a class named Bibliotecario

    @Id // Indicates the primary key field of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Integer id; // Declaring an Integer field named id to store the primary key

    @Column(nullable = false) // Specifies the mapping for a database column
    private String nome; // Declaring a String field to store the librarian's name

    @Column(nullable = false) // Specifies the mapping for a database column
    private String email; // Declaring a String field to store the librarian's email

    @Column(nullable = false) // Specifies the mapping for a database column
    private String senha; // Declaring a String field to store the librarian's password

    public Bibliotecario() { // Default constructor for the Bibliotecario class
    }

    public Bibliotecario(String nome, String email, String senha) { // Constructor with parameters for the Bibliotecario class
        this.nome = nome; // Assigning the provided name to the instance variable nome
        this.email = email; // Assigning the provided email to the instance variable email
        this.senha = senha; // Assigning the provided password to the instance variable senha
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

    // Getter method for retrieving the email field
    public String getEmail() {
        return email;
    }

    // Setter method for setting the email field
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for retrieving the senha field
    public String getSenha() {
        return senha;
    }

    // Setter method for setting the senha field
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

