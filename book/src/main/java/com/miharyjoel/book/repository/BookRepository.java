package com.miharyjoel.book.repository;

import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("""
    SELECT book
    FROM Book book
    WHERE book.archived = false
    AND book.shareable = true
    AND book.owner.id != :userId
    """)
  Page<Book> findAllDisplayableBoos(Pageable pageable, Long userId);
}
