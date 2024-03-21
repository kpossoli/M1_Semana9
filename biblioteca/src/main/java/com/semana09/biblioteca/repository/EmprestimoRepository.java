package com.semana09.biblioteca.repository;

import com.semana09.biblioteca.model.Emprestimo; // Importing the Emprestimo model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository interface
import org.springframework.stereotype.Repository; // Importing Repository annotation

@Repository // Indicates that the class is a repository component in the Spring application context
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> { // Declaring an interface named EmprestimoRepository that extends JpaRepository

}
