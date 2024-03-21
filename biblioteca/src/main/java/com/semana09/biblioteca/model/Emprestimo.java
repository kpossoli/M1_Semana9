package com.semana09.biblioteca.model;

import jakarta.persistence.*; // Importing Jakarta Persistence API annotations
import lombok.Data; // Importing Lombok @Data annotation for automatic generation of getters, setters, toString, etc.
import java.time.LocalDate; // Importing LocalDate class from java.time package

@Entity // Marks the class as an entity, indicating it will be mapped to a database table
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class Emprestimo { // Declaring a class named Emprestimo

    @Id // Indicates the primary key field of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Integer id; // Declaring an Integer field named id to store the primary key

    @ManyToOne // Defines a many-to-one relationship with another entity
    @JoinColumn(name = "id_livro") // Specifies the foreign key column name
    private Livro livro; // Declaring a field to represent the book being loaned

    @ManyToOne // Defines a many-to-one relationship with another entity
    @JoinColumn(name = "id_membro") // Specifies the foreign key column name
    private Membro membro; // Declaring a field to represent the member who borrowed the book

    @Column(nullable = false) // Specifies the mapping for a database column
    private LocalDate dataEmprestimo; // Declaring a field to store the loan date

    @Column(nullable = false) // Specifies the mapping for a database column
    private LocalDate dataDevolucao; // Declaring a field to store the return date

    public Emprestimo() { // Default constructor for the Emprestimo class
    }

    public Emprestimo(Livro livro, Membro membro, LocalDate dataEmprestimo, LocalDate dataDevolucao) { // Constructor with parameters for the Emprestimo class
        this.livro = livro; // Assigning the provided Livro object to the instance variable livro
        this.membro = membro; // Assigning the provided Membro object to the instance variable membro
        this.dataEmprestimo = dataEmprestimo; // Assigning the provided loan date to the instance variable dataEmprestimo
        this.dataDevolucao = dataDevolucao; // Assigning the provided return date to the instance variable dataDevolucao
    }

    // Getter method for retrieving the id field
    public Integer getId() {
        return id;
    }

    // Getter method for retrieving the dataEmprestimo field
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    // Setter method for setting the dataEmprestimo field
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    // Getter method for retrieving the dataDevolucao field
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    // Setter method for setting the dataDevolucao field
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Getter method for retrieving the livro field
    public Livro getLivro() {
        return livro;
    }

    // Setter method for setting the livro field
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    // Getter method for retrieving the membro field
    public Membro getMembro() {
        return membro;
    }

    // Setter method for setting the membro field
    public void setMembro(Membro membro) {
        this.membro = membro;
    }
}

