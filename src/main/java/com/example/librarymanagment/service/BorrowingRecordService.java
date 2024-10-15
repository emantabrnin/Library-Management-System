package com.example.librarymanagment.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagment.entity.Book;
import com.example.librarymanagment.entity.BorrowingRecord;
import com.example.librarymanagment.entity.Patron;
import com.example.librarymanagment.repository.BookRepository;
import com.example.librarymanagment.repository.BorrrowingRecordRepo;
import com.example.librarymanagment.repository.PatronRepository;

@Service
public class BorrowingRecordService  extends BaseService<BorrowingRecord>{
    @Autowired
    public BorrrowingRecordRepo brrrowingRecordRepo;

    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;
    
    public BorrowingRecordService(BorrrowingRecordRepo brrrowingRecordRepo){
        super(brrrowingRecordRepo);
        this.brrrowingRecordRepo =brrrowingRecordRepo;
    }

    @Transactional
    public String borrowBook(UUID bookId, UUID patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        if (book.getAvailableCopies() <= 0) {
            return "No copies available";
        }

        // Create a new borrowing record
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowingDate(LocalDate.now());
        record.setStatus("borrowed");

        // Update available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        brrrowingRecordRepo.save(record);
        return "Book borrowed successfully";
    }

    @Transactional
    public String returnBook(UUID bookId, UUID patronId) {
        // Find the borrowing record for the given book and patron
        BorrowingRecord record = brrrowingRecordRepo.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("No borrowing record found"));

        // Update the book's available copies
        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        // Update the borrowing record status
        record.setStatus("returned");
        record.setReturnDate(LocalDate.now());
        brrrowingRecordRepo.save(record);
        return "Book returned successfully";
    }

    
}
