package com.miharyjoel.book.repository;

import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Long> {

  @Query("""
    SELECT history
    FROM BookTransactionHistory history
    WHERE history.user.id = :userId
    """)
  Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Long userId);



}
