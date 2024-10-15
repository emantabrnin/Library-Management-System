package com.example.librarymanagment.entity;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
public class Book  extends BaseEntity{
    
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private int availableCopies;

    public Book(UUID id ,String title, String author, int publicationYear, String isbn,int availableCopies) {
        this.id=id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.availableCopies=availableCopies;
    }
    }