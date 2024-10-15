package com.example.librarymanagment.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.librarymanagment.entity.BorrowingRecord;

@Repository
public interface BorrrowingRecordRepo extends BaseRepository<BorrowingRecord>{
    Optional<BorrowingRecord> findByBookIdAndPatronId(UUID bookId, UUID patronId);
}
