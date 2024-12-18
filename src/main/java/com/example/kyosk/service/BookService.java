package com.example.kyosk.service;

import com.example.kyosk.api.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookrepo;

    public BookService(BookRepository bookrepo){
        this.bookrepo = bookrepo;
    }
    public List<Book> getAllBooks(){
        return  bookrepo.findAll();
    }
}
