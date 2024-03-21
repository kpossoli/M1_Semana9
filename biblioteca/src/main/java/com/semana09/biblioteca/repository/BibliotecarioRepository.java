package com.semana09.biblioteca.repository;

import com.semana09.biblioteca.model.Bibliotecario; // Importing the Bibliotecario model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface
import org.springframework.data.jpa.repository.Query; // Importing Query annotation
import org.springframework.stereotype.Repository; // Importing Repository annotation

import java.util.ArrayList; // Importing ArrayList class from java.util package

@Repository // Indicates that the class is a repository component in the Spring application context
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer> { // Declaring an interface named BibliotecarioRepository that extends JpaRepository

    @Query("SELECT b.email FROM Bibliotecario b") // Defines a JPQL query to retrieve emails of all bibliotecarios
    ArrayList<String> buscarEmailsDosBibliotecarios(); // Method signature for searching emails of all bibliotecarios

}
