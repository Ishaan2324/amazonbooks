package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Book;
import com.ecommerce.backend.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    // GET ALL BOOKS
    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // GET BOOK BY ID
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // ADD NEW BOOK
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    // UPDATE BOOK
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,
                           @RequestBody Book updatedBook) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        book.setPrice(updatedBook.getPrice());
        book.setImageUrl(updatedBook.getImageUrl());
        book.setStockQuantity(updatedBook.getStockQuantity());

        return repository.save(book);
    }

    // DELETE BOOK
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "Book Deleted Successfully";
    }
}
