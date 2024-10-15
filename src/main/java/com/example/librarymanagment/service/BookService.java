package com.example.librarymanagment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagment.entity.Book;
import com.example.librarymanagment.repository.BookRepository;

@Service
public class BookService extends BaseService<Book>{
    @Autowired
    public BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        super(bookRepository);
        this.bookRepository = bookRepository;
    } 
}
