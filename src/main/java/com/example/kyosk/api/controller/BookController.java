package com.example.kyosk.api.controller;

import com.example.kyosk.api.model.Book;
import com.example.kyosk.service.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;

    }
    @GetMapping("/api/books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}