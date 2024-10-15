package com.example.librarymanagment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagment.entity.Patron;
import com.example.librarymanagment.repository.PatronRepository;
@Service
public class PatronService  extends BaseService<Patron>{

    @Autowired
    public PatronRepository patronRepository;

    public PatronService (PatronRepository patronRepository){
        super(patronRepository);
        this.patronRepository=patronRepository;
    }
    
}
