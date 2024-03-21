package com.semana09.biblioteca.repository;

import com.semana09.biblioteca.model.Livro; // Importing the Livro model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface
import org.springframework.data.jpa.repository.Query; // Importing Query annotation
import org.springframework.stereotype.Repository; // Importing Repository annotation

import java.util.ArrayList; // Importing ArrayList class from java.util package

@Repository // Indicates that the class is a repository component in the Spring application context
public interface LivroRepository extends JpaRepository<Livro, Integer> { // Declaring an interface named LivroRepository that extends JpaRepository

    @Query("SELECT l.titulo FROM Livro l") // Declaring a query to retrieve titles of books
    ArrayList<String> buscarTitulosDosLivros(); // Declaring a method to execute the query and return titles of books

    @Query("SELECT l.autor FROM Livro l") // Declaring a query to retrieve authors of books
    ArrayList<String> buscarAutoresDosLivros(); // Declaring a method to execute the query and return authors of books

}
