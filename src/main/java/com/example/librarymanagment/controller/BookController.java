package com.example.librarymanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagment.BaseController;
import com.example.librarymanagment.entity.Book;
import com.example.librarymanagment.service.BookService;


@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController<Book>{
    @Autowired
    public BookService bookService;

    public BookController(BookService bookService){
        super(bookService);
        this.bookService=bookService;
    }  
}
