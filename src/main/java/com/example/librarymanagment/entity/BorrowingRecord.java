package com.example.librarymanagment.entity;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "borrow_record")
public class BorrowingRecord extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    private LocalDate borrowingDate;
    private LocalDate returnDate; 
    private String status; // borrowed, returned, etc.
}
