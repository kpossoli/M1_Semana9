package com.semana09.biblioteca.controller;

import com.semana09.biblioteca.model.Bibliotecario; // Importing the Bibliotecario model class
import com.semana09.biblioteca.repository.BibliotecarioRepository; // Importing the BibliotecarioRepository interface
import org.springframework.http.HttpStatus; // Importing HttpStatus enum from org.springframework.http package
import org.springframework.http.ResponseEntity; // Importing ResponseEntity class from org.springframework.http package
import org.springframework.web.bind.annotation.*; // Importing annotations for Spring Web MVC

import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.Optional; // Importing Optional class from java.util package

@RestController // Marks the class as a RESTful controller component
@RequestMapping("/bibliotecario") // Specifies the base URI path for mapping HTTP requests
public class BibliotecarioController { // Declaring a class named BibliotecarioController for handling bibliotecario-related HTTP requests

    BibliotecarioRepository bibliotecarioRepository; // Declaring a variable to hold a reference to BibliotecarioRepository

    public BibliotecarioController(BibliotecarioRepository bibliotecarioRepository) { // Constructor for BibliotecarioController class
        this.bibliotecarioRepository = bibliotecarioRepository; // Initializing the BibliotecarioRepository variable with the provided repository
    }

    @PostMapping // Handles HTTP POST requests
    public ResponseEntity<Bibliotecario> criarUmBibliotecario(@RequestBody Bibliotecario bibliotecario) { // Method to create a new bibliotecario
        Bibliotecario novoBibliotecario = bibliotecarioRepository.save(bibliotecario); // Saving the new bibliotecario using BibliotecarioRepository
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBibliotecario); // Returning ResponseEntity with status and body
    }

    @GetMapping // Handles HTTP GET requests
    public ResponseEntity<ArrayList<Bibliotecario>> buscarTodosOsBibliotecarios() { // Method to retrieve all bibliotecarios
        ArrayList<Bibliotecario> bibliotecarios = (ArrayList<Bibliotecario>) bibliotecarioRepository.findAll(); // Retrieving all bibliotecarios using BibliotecarioRepository
        if (bibliotecarios.isEmpty()) { // Checking if the list of bibliotecarios is empty
            return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
        } else {
            return ResponseEntity.ok(bibliotecarios); // Returning ResponseEntity with OK status and list of bibliotecarios
        }
    }

    @GetMapping("{idBibliotecario}") // Handles HTTP GET requests with a specific bibliotecario ID
    public ResponseEntity<Bibliotecario> buscarUmBibliotecario(@PathVariable Integer idBibliotecario) { // Method to retrieve a specific bibliotecario
        Optional<Bibliotecario> bibliotecarioEncontrado = bibliotecarioRepository.findById(idBibliotecario); // Retrieving the bibliotecario by its ID using BibliotecarioRepository
        return bibliotecarioEncontrado.map( // Mapping the Optional result to a ResponseEntity
                bibliotecario -> new ResponseEntity<>(bibliotecario, HttpStatus.FOUND) // Returning ResponseEntity with found bibliotecario and status
        ).orElseGet( // If the bibliotecario is not found
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND) // Returning ResponseEntity with not found status
        );
    }

    @PutMapping("{idBibliotecario}") // Handles HTTP PUT requests with a specific bibliotecario ID
    public ResponseEntity<Optional<Bibliotecario>> atualizarUmBibliotecario(@PathVariable Integer idBibliotecario, @RequestBody Bibliotecario bibliotecarioAtualizado) { // Method to update a specific bibliotecario
        Optional<Bibliotecario> bibliotecarioParaAtualizar = bibliotecarioRepository.findById(idBibliotecario); // Retrieving the bibliotecario to update by its ID using BibliotecarioRepository
        if (bibliotecarioParaAtualizar.isPresent()) { // Checking if the bibliotecario to update exists
            Bibliotecario bibliotecarioExistente = bibliotecarioParaAtualizar.get(); // Getting the existing bibliotecario
            bibliotecarioExistente.setNome(bibliotecarioAtualizado.getNome()); // Updating the nome field
            bibliotecarioExistente.setEmail(bibliotecarioAtualizado.getEmail()); // Updating the email field
            bibliotecarioExistente.setSenha(bibliotecarioAtualizado.getSenha()); // Updating the senha field
            Bibliotecario bibliotecarioAtualizadoSalvo = bibliotecarioRepository.save(bibliotecarioExistente); // Saving the updated bibliotecario using BibliotecarioRepository
            return ResponseEntity.ok(Optional.of(bibliotecarioAtualizadoSalvo)); // Returning ResponseEntity with OK status and updated bibliotecario
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returning ResponseEntity with not found status if the bibliotecario to update is not found
        }
    }

    @DeleteMapping("{idBibliotecario}") // Handles HTTP DELETE requests with a specific bibliotecario ID
    public ResponseEntity<Void> deletarUmBibliotecario(@PathVariable Integer idBibliotecario) { // Method to delete a specific bibliotecario
        bibliotecarioRepository.deleteById(idBibliotecario); // Deleting the bibliotecario by its ID using BibliotecarioRepository
        return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
    }

    @GetMapping("/emails") // Handles HTTP GET requests to retrieve emails of all bibliotecarios
    public ResponseEntity<ArrayList<String>> buscarEmailsDosBibliotecarios() { // Method to retrieve emails of all bibliotecarios
        ArrayList<String> emails = bibliotecarioRepository.buscarEmailsDosBibliotecarios(); // Retrieving emails of all bibliotecarios using BibliotecarioRepository
        return ResponseEntity.ok(emails); // Returning ResponseEntity with OK status and list of emails
    }

    @PutMapping("{idBibliotecario}/senha") // Handles HTTP PUT requests to update the senha field of a specific bibliotecario
    public ResponseEntity<Void> atualizarSenhaDoBibliotecario(@PathVariable Integer idBibliotecario, @RequestBody String novaSenha) { // Method to update the senha field of a specific bibliotecario
        Optional<Bibliotecario> bibliotecarioOptional = bibliotecarioRepository.findById(idBibliotecario); // Retrieving the bibliotecario by its ID using BibliotecarioRepository
        if (bibliotecarioOptional.isPresent()) { // Checking if the bibliotecario exists
            Bibliotecario bibliotecario = bibliotecarioOptional.get(); // Getting the existing bibliotecario
            bibliotecario.setSenha(novaSenha); // Updating the senha field
            bibliotecarioRepository.save(bibliotecario); // Saving the updated bibliotecario using BibliotecarioRepository
            return ResponseEntity.ok().build(); // Returning ResponseEntity with OK status
        } else {
            return ResponseEntity.notFound().build(); // Returning ResponseEntity with not found status if the bibliotecario is not found
        }
    }

}
