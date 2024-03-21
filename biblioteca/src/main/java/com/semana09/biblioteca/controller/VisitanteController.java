package com.semana09.biblioteca.controller;

import com.semana09.biblioteca.model.Visitante; // Importing the Visitante model class
import com.semana09.biblioteca.repository.VisitanteRepository; // Importing the VisitanteRepository interface
import org.springframework.http.HttpStatus; // Importing HttpStatus enum from Spring framework
import org.springframework.http.ResponseEntity; // Importing ResponseEntity class from Spring framework
import org.springframework.web.bind.annotation.*; // Importing annotations for Spring MVC

import java.util.ArrayList; // Importing ArrayList class from Java util package
import java.util.Optional; // Importing Optional class from Java util package

@RestController // Indicates that this class contains request-handling methods
@RequestMapping("/visitante") // Maps HTTP requests to handler methods of this class
public class VisitanteController { // Declaring a class named VisitanteController

    VisitanteRepository visitanteRepository; // Declaring a VisitanteRepository field

    public VisitanteController(VisitanteRepository visitanteRepository) { // Constructor for VisitanteController
        this.visitanteRepository = visitanteRepository; // Initializing the VisitanteRepository field
    }

    @PostMapping // Annotation for mapping HTTP POST requests onto specific handler methods
    public ResponseEntity<Visitante> criarUmVisitante(@RequestBody Visitante visitante) { // Method for creating a new Visitante
        Visitante novoVisitante = visitanteRepository.save(visitante); // Saving the new Visitante using the repository
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVisitante); // Returning a ResponseEntity with the newly created Visitante
    }

    @GetMapping // Annotation for mapping HTTP GET requests onto specific handler methods
    public ResponseEntity<ArrayList<Visitante>> buscarTodosOsvisitantes() { // Method for retrieving all Visitantes
        ArrayList<Visitante> visitantes = (ArrayList<Visitante>) visitanteRepository.findAll(); // Retrieving all Visitantes using the repository
        if (visitantes.isEmpty()) { // Checking if the list of Visitantes is empty
            return ResponseEntity.noContent().build(); // Returning a ResponseEntity with status code NO_CONTENT
        } else {
            return ResponseEntity.ok(visitantes); // Returning a ResponseEntity with the list of Visitantes
        }
    }

    @GetMapping("{idVisitante}") // Annotation for mapping HTTP GET requests onto specific handler methods with a path variable
    public ResponseEntity<Visitante> buscarUmVisitante(@PathVariable Integer idVisitante) { // Method for retrieving a specific Visitante by ID
        Optional<Visitante> visitanteEncontrado = visitanteRepository.findById(idVisitante); // Retrieving the Visitante by ID using the repository
        return visitanteEncontrado.map( // Mapping the Optional<Visitante> to ResponseEntity<Visitante>
                visitante -> new ResponseEntity<>(visitante, HttpStatus.FOUND) // If Visitante is found, returning a ResponseEntity with status code FOUND
        ).orElseGet( // If Visitante is not found
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND) // Returning a ResponseEntity with status code NOT_FOUND
        );
    }

    @PutMapping("{idVisitante}") // Annotation for mapping HTTP PUT requests onto specific handler methods with a path variable
    public ResponseEntity<Optional<Visitante>> atualizarUmVisitante(@PathVariable Integer idVisitante, @RequestBody Visitante visitanteAtualizado) { // Method for updating a Visitante
        Optional<Visitante> visitanteParaAtualizar = visitanteRepository.findById(idVisitante); // Retrieving the Visitante by ID using the repository
        if (visitanteParaAtualizar.isPresent()) { // Checking if the Visitante to update exists
            Visitante visitanteExistente = visitanteParaAtualizar.get(); // Getting the existing Visitante
            visitanteExistente.setNome(visitanteAtualizado.getNome()); // Updating the Visitante's nome
            visitanteExistente.setTelefone(visitanteAtualizado.getTelefone()); // Updating the Visitante's telefone
            Visitante visitanteAtualizadoSalvo = visitanteRepository.save(visitanteExistente); // Saving the updated Visitante using the repository
            return ResponseEntity.ok(Optional.of(visitanteAtualizadoSalvo)); // Returning a ResponseEntity with the updated Visitante
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returning a ResponseEntity with status code NOT_FOUND if the Visitante is not found
        }
    }

    @DeleteMapping("{idVisitante}") // Annotation for mapping HTTP DELETE requests onto specific handler methods with a path variable
    public ResponseEntity<Void> deletarUmVisitante(@PathVariable Integer idVisitante) { // Method for deleting a Visitante
        visitanteRepository.deleteById(idVisitante); // Deleting the Visitante by ID using the repository
        return ResponseEntity.noContent().build(); // Returning a ResponseEntity with status code NO_CONTENT
    }
}

