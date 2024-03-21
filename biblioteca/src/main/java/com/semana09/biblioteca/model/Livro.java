package com.semana09.biblioteca.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity // Marks the class as an entity, indicating it will be mapped to a database table
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class Livro {

    @Id // Indicates the primary key field of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Integer id; // Declaring an Integer field named id to store the primary key

    @Column(nullable = false) // Specifies the mapping for a database column
    private String titulo; // Declaring a String field named titulo to store the title of the book

    @Column(nullable = false) // Specifies the mapping for a database column
    private String autor; // Declaring a String field named autor to store the author of the book

    @Column // Specifies the mapping for a database column
    private Integer anoPublicacao; // Declaring an Integer field named anoPublicacao to store the publication year of the book

    public Livro() { // Default constructor for the Livro class
    }

    public Livro(String titulo, String autor, Integer anoPublicacao) { // Constructor with parameters for the Livro class
        this.titulo = titulo; // Assigning the provided title to the instance variable titulo
        this.autor = autor; // Assigning the provided author to the instance variable autor
        this.anoPublicacao = anoPublicacao; // Assigning the provided publication year to the instance variable anoPublicacao
    }

    // Getter method for retrieving the id field
    public Integer getId() {
        return id;
    }

    // Getter method for retrieving the titulo field
    public String getTitulo() {
        return titulo;
    }

    // Setter method for setting the titulo field
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter method for retrieving the autor field
    public String getAutor() {
        return autor;
    }

    // Setter method for setting the autor field
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Getter method for retrieving the anoPublicacao field
    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    // Setter method for setting the anoPublicacao field
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

}
