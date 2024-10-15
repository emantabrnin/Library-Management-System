package com.example.librarymanagment.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagment.BaseController;
import com.example.librarymanagment.entity.BorrowingRecord;
import com.example.librarymanagment.service.BorrowingRecordService;


@RestController
@RequestMapping("/api")
public class BorrwingRecordController extends BaseController<BorrowingRecord>{
    @Autowired
    BorrowingRecordService borrowingRecordService;

    public BorrwingRecordController (BorrowingRecordService borrowingRecordService){
        super(borrowingRecordService);
        this.borrowingRecordService=borrowingRecordService;
    }

     @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable UUID bookId, @PathVariable UUID patronId) {
        String result = borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable UUID bookId, @PathVariable UUID patronId) {
        String result = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(result);
    }
    
}
