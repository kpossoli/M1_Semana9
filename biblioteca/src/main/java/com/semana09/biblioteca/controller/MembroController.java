package com.semana09.biblioteca.controller;

import com.semana09.biblioteca.model.Membro;
import com.semana09.biblioteca.repository.MembroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController // Marks the class as a RESTful controller component
@RequestMapping("/membro") // Specifies the base URI path for mapping HTTP requests
public class MembroController { // Declaring a class named MembroController for handling membro-related HTTP requests

    MembroRepository membroRepository; // Declaring a variable to hold a reference to MembroRepository

    public MembroController(MembroRepository membroRepository) { // Constructor for MembroController class
        this.membroRepository = membroRepository; // Initializing the MembroRepository variable with the provided repository
    }

    @PostMapping // Handles HTTP POST requests
    public ResponseEntity<Membro> criarUmMembro(@RequestBody Membro membro) { // Method to create a new membro
        Membro novoMembro = membroRepository.save(membro); // Saving the new membro using MembroRepository
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMembro); // Returning ResponseEntity with status and body
    }

    @GetMapping // Handles HTTP GET requests
    public ResponseEntity<ArrayList<Membro>> buscarTodosOsMembros() { // Method to retrieve all membros
        ArrayList<Membro> membros = (ArrayList<Membro>) membroRepository.findAll(); // Retrieving all membros using MembroRepository
        if (membros.isEmpty()) { // Checking if the list of membros is empty
            return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
        } else {
            return ResponseEntity.ok(membros); // Returning ResponseEntity with OK status and list of membros
        }
    }

    @GetMapping("{idMembro}") // Handles HTTP GET requests with a specific membro ID
    public ResponseEntity<Membro> buscarUmMembro(@PathVariable Integer idMembro) { // Method to retrieve a specific membro
        Optional<Membro> membroEncontrado = membroRepository.findById(idMembro); // Retrieving the membro by its ID using MembroRepository
        return membroEncontrado.map( // Mapping the Optional result to a ResponseEntity
                membro -> new ResponseEntity<>(membro, HttpStatus.FOUND) // Returning ResponseEntity with found membro and status
        ).orElseGet( // If the membro is not found
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND) // Returning ResponseEntity with not found status
        );
    }

    @PutMapping("{idMembro}") // Handles HTTP PUT requests with a specific membro ID
    public ResponseEntity<Optional<Membro>> atualizandoUmMembro(@PathVariable Integer idMembro, @RequestBody Membro membroAtualizado) {
        Optional<Membro> membroParaAtualizar = membroRepository.findById(idMembro);
        if (membroParaAtualizar.isPresent()) {
            Membro membroExistente = membroParaAtualizar.get();
            membroExistente.setNome(membroAtualizado.getNome());
            membroExistente.setTelefone(membroAtualizado.getTelefone());
            membroExistente.setEndereco(membroAtualizado.getEndereco());
            Membro membroAtualizadoSalvo = membroRepository.save(membroExistente);
            return ResponseEntity.ok(Optional.of(membroAtualizadoSalvo));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{idMembro}") // Handles HTTP DELETE requests with a specific membro ID
    public ResponseEntity<Void> deletarUmMembro(@PathVariable Integer idMembro) { // Method to delete a specific membro
        membroRepository.deleteById(idMembro); // Deleting the membro by its ID using MembroRepository
        return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
    }

    @GetMapping("/nomes") // Handles HTTP GET requests to retrieve membro names
    public ResponseEntity<ArrayList<String>> buscarNomesDosMembros() { // Method to retrieve membro names
        ArrayList<String> nomes = membroRepository.buscarNomesDosMembros(); // Retrieving membro names using MembroRepository
        return ResponseEntity.ok(nomes); // Returning ResponseEntity with OK status and list of names
    }

    @GetMapping("/telefones") // Handles HTTP GET requests to retrieve membro phone numbers
    public ResponseEntity<ArrayList<String>> buscarTelefonesDosMembros() { // Method to retrieve membro phone numbers
        ArrayList<String> telefones = membroRepository.buscarTelefonesDosMembros(); // Retrieving membro phone numbers using MembroRepository
        return ResponseEntity.ok(telefones); // Returning ResponseEntity with OK status and list of phone numbers
    }

    @PutMapping("{idMembro}/telefone") // Handles HTTP PUT requests to update membro phone number
    public ResponseEntity<Void> atualizarTelefoneDoMembro(@PathVariable Integer idMembro, @RequestBody String novoTelefone) {
        Optional<Membro> membroOptional = membroRepository.findById(idMembro);
        if (membroOptional.isPresent()) {
            Membro membro = membroOptional.get();
            membro.setTelefone(novoTelefone);
            membroRepository.save(membro);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
