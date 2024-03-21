package com.semana09.biblioteca.controller;

import com.semana09.biblioteca.model.Emprestimo; // Importing the Emprestimo model class
import com.semana09.biblioteca.repository.EmprestimoRepository; // Importing the EmprestimoRepository interface
import org.springframework.http.HttpStatus; // Importing HttpStatus enum from org.springframework.http package
import org.springframework.http.ResponseEntity; // Importing ResponseEntity class from org.springframework.http package
import org.springframework.web.bind.annotation.*; // Importing annotations for Spring Web MVC

import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.Optional; // Importing Optional class from java.util package

@RestController // Marks the class as a RESTful controller component
@RequestMapping("/emprestimo") // Specifies the base URI path for mapping HTTP requests
public class EmprestimoController { // Declaring a class named EmprestimoController for handling emprestimo-related HTTP requests

    EmprestimoRepository emprestimoRepository; // Declaring a variable to hold a reference to EmprestimoRepository

    public EmprestimoController(EmprestimoRepository emprestimoRepository) { // Constructor for EmprestimoController class
        this.emprestimoRepository = emprestimoRepository; // Initializing the EmprestimoRepository variable with the provided repository
    }

    @PostMapping // Handles HTTP POST requests
    public ResponseEntity<Emprestimo> criarUmEmprestimo(@RequestBody Emprestimo emprestimo) { // Method to create a new emprestimo
        Emprestimo novoEmprestimo = emprestimoRepository.save(emprestimo); // Saving the new emprestimo using EmprestimoRepository
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo); // Returning ResponseEntity with status and body
    }

    @GetMapping // Handles HTTP GET requests
    public ResponseEntity<ArrayList<Emprestimo>> buscarTodosOsEmprestimos() { // Method to retrieve all emprestimos
        ArrayList<Emprestimo> emprestimos = (ArrayList<Emprestimo>) emprestimoRepository.findAll(); // Retrieving all emprestimos using EmprestimoRepository
        if (emprestimos.isEmpty()) { // Checking if the list of emprestimos is empty
            return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
        } else {
            return ResponseEntity.ok(emprestimos); // Returning ResponseEntity with OK status and list of emprestimos
        }
    }

    @GetMapping("{idEmprestimo}") // Handles HTTP GET requests with a specific emprestimo ID
    public ResponseEntity<Emprestimo> buscarUmEmprestimo(@PathVariable Integer idEmprestimo) { // Method to retrieve a specific emprestimo
        Optional<Emprestimo> emprestimoEncontrado = emprestimoRepository.findById(idEmprestimo); // Retrieving the emprestimo by its ID using EmprestimoRepository
        return emprestimoEncontrado.map( // Mapping the Optional result to a ResponseEntity
                emprestimo -> new ResponseEntity<>(emprestimo, HttpStatus.FOUND) // Returning ResponseEntity with found emprestimo and status
        ).orElseGet( // If the emprestimo is not found
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND) // Returning ResponseEntity with not found status
        );
    }

    @PutMapping("{idEmprestimo}") // Handles HTTP PUT requests with a specific emprestimo ID
    public ResponseEntity<Optional<Emprestimo>> atualizarUmEmprestimo(@PathVariable Integer idEmprestimo, @RequestBody Emprestimo emprestimoAtualizado) {
        Optional<Emprestimo> emprestimoParaAtualizar = emprestimoRepository.findById(idEmprestimo);
        if (emprestimoParaAtualizar.isPresent()) {
            Emprestimo emprestimoExistente = emprestimoParaAtualizar.get();
            emprestimoExistente.setDataEmprestimo(emprestimoAtualizado.getDataEmprestimo());
            emprestimoExistente.setDataDevolucao(emprestimoAtualizado.getDataDevolucao());
            emprestimoExistente.setLivro(emprestimoAtualizado.getLivro());
            emprestimoExistente.setMembro(emprestimoAtualizado.getMembro());

            Emprestimo emprestimoAtualizadoSalvo = emprestimoRepository.save(emprestimoExistente);
            return ResponseEntity.ok(Optional.of(emprestimoAtualizadoSalvo));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{idEmprestimo}") // Handles HTTP DELETE requests with a specific emprestimo ID
    public ResponseEntity<Void> deletarUmEmprestimo(@PathVariable Integer idEmprestimo) { // Method to delete a specific emprestimo
        emprestimoRepository.deleteById(idEmprestimo); // Deleting the emprestimo by its ID using EmprestimoRepository
        return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
    }

}