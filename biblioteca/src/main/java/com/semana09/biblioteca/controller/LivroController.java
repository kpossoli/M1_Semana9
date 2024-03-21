package com.semana09.biblioteca.controller;

import com.semana09.biblioteca.model.Livro; // Importing the Livro model class from the library package
import com.semana09.biblioteca.repository.LivroRepository; // Importing the LivroRepository interface from the repository package
import org.springframework.web.bind.annotation.*; // Importing annotations for Spring Web MVC
import org.springframework.http.HttpStatus; // Importing HttpStatus enum from org.springframework.http package
import org.springframework.http.ResponseEntity; // Importing ResponseEntity class from org.springframework.http package

import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.Optional; // Importing Optional class from java.util package

@RestController // Marks the class as a RESTful controller component
@RequestMapping("/livro") // Specifies the base URI path for mapping HTTP requests
public class LivroController { // Declaring a class named LivroController for handling livro-related HTTP requests

    LivroRepository livroRepository; // Declaring a variable to hold a reference to LivroRepository

    public LivroController(LivroRepository livroRepository) { // Constructor for LivroController class
        this.livroRepository = livroRepository; // Initializing the LivroRepository variable with the provided repository
    }

    @PostMapping // Handles HTTP POST requests
    public ResponseEntity<Livro> criarUmLivro(@RequestBody Livro livro) { // Method to create a new book
        Livro novoLivro = livroRepository.save(livro); // Saving the new book using LivroRepository
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro); // Returning ResponseEntity with status and body
    }

    @GetMapping // Handles HTTP GET requests
    public ResponseEntity<ArrayList<Livro>> buscarTodosOsLivros() { // Method to retrieve all books
        ArrayList<Livro> livros = (ArrayList<Livro>) livroRepository.findAll(); // Retrieving all books using LivroRepository
        if (livros.isEmpty()) { // Checking if the list of books is empty
            return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
        } else {
            return ResponseEntity.ok(livros); // Returning ResponseEntity with OK status and list of books
        }
    }

    @GetMapping("{idLivro}") // Handles HTTP GET requests with a specific book ID
    public ResponseEntity<Livro> buscarUmLivro(@PathVariable Integer idLivro) { // Method to retrieve a specific book
        Optional<Livro> livroEncontrado = livroRepository.findById(idLivro); // Retrieving the book by its ID using LivroRepository
        return livroEncontrado.map( // Mapping the Optional result to a ResponseEntity
                livro -> new ResponseEntity<>(livro, HttpStatus.FOUND) // Returning ResponseEntity with found book and status
        ).orElseGet( // If the book is not found
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND) // Returning ResponseEntity with not found status
        );
    }

    @PutMapping("{idLivro}") // Handles HTTP PUT requests with a specific book ID
    public ResponseEntity<Optional<Livro>> atualizarUmLivro(@PathVariable Integer idLivro, @RequestBody Livro livroAtualizado) {
        Optional<Livro> livroParaAtualizar = livroRepository.findById(idLivro);
        if (livroParaAtualizar.isPresent()) {
            Livro livroExistente = livroParaAtualizar.get();
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            Livro livroAtualizadoSalvo = livroRepository.save(livroExistente);
            return ResponseEntity.ok(Optional.of(livroAtualizadoSalvo));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{idLivro}") // Handles HTTP DELETE requests with a specific book ID
    public ResponseEntity<Void> deletarUmLivro(@PathVariable Integer idLivro) { // Method to delete a specific book
        livroRepository.deleteById(idLivro); // Deleting the book by its ID using LivroRepository
        return ResponseEntity.noContent().build(); // Returning ResponseEntity with no content status
    }

    @GetMapping("/titulos") // Handles HTTP GET requests to retrieve book titles
    public ResponseEntity<ArrayList<String>> buscarTitulosDosLivros() { // Method to retrieve book titles
        ArrayList<String> titulos = livroRepository.buscarTitulosDosLivros(); // Retrieving book titles using LivroRepository
        return ResponseEntity.ok(titulos); // Returning ResponseEntity with OK status and list of titles
    }

    @GetMapping("/autores") // Handles HTTP GET requests to retrieve book authors
    public ResponseEntity<ArrayList<String>> buscarAutoresDosLivros() { // Method to retrieve book authors
        ArrayList<String> autores = livroRepository.buscarAutoresDosLivros(); // Retrieving book authors using LivroRepository
        return ResponseEntity.ok(autores); // Returning ResponseEntity with OK status and list of authors
    }
}