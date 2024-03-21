package com.semana09.biblioteca.repository;

import com.semana09.biblioteca.model.Visitante; // Importing the Visitante model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface from Spring Data JPA
import org.springframework.stereotype.Repository; // Importing Repository annotation from Spring framework

@Repository // Indicates that the class is a repository component in the Spring application context
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> { // Declaring an interface named VisitanteRepository that extends JpaRepository

}
