package com.example.librarymanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagment.BaseController;
import com.example.librarymanagment.entity.Patron;
import com.example.librarymanagment.service.PatronService;

@RestController
@RequestMapping("/api/patrons")
public class PatronController extends BaseController<Patron>{
    
    @Autowired
    public PatronService patronService;

    public PatronController(PatronService patronService){
        super(patronService);
        this.patronService=patronService;
    }

}
