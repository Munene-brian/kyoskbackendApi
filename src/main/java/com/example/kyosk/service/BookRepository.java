package com.example.kyosk.service;

import com.example.kyosk.api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository <Book, String> {
}
