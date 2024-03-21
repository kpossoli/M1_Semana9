package com.semana09.biblioteca.repository;

import com.semana09.biblioteca.model.Membro; // Importing the Membro model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface
import org.springframework.data.jpa.repository.Query; // Importing Query annotation
import org.springframework.stereotype.Repository; // Importing Repository annotation

import java.util.ArrayList; // Importing ArrayList class from java.util package

@Repository // Indicates that the class is a repository component in the Spring application context
public interface MembroRepository extends JpaRepository<Membro, Integer> { // Declaring an interface named MembroRepository that extends JpaRepository

    @Query("SELECT m.nome FROM Membro m") // Declaring a query to retrieve names of members
    ArrayList<String> buscarNomesDosMembros(); // Declaring a method to execute the query and return names of members

    @Query("SELECT m.telefone FROM Membro m") // Declaring a query to retrieve phone numbers of members
    ArrayList<String> buscarTelefonesDosMembros(); // Declaring a method to execute the query and return phone numbers of members

}
