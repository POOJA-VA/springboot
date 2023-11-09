package com.codewithpooja.spring.controller;

import com.codewithpooja.spring.model.Book;
import com.codewithpooja.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/book")
    public List<Book> addBook(@RequestBody Book book) {
        return bookRepository.insert(book);
    }

    @PutMapping("/book")
    public List<Book> updateBook(@RequestBody Book book) {
        return bookRepository.update(book);
    }

    @DeleteMapping("/book/{id}")
    public List<Book> deleteBook(@PathVariable int id) {
        return bookRepository.deleteById(id);
    }
}