package com.miharyjoel.book.dto.mapper;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.dto.BookResponse;
import com.miharyjoel.book.dto.BorrowedBookResponse;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

  public Book toBook(BookRequest request) {
    return Book.builder()
      .id(request.id())
      .title(request.title())
      .authorName(request.authorName())
      .synopsis(request.synopsis())
      .archived(false)
      .shareable(request.shareable())
      .build();
  }

  public BookResponse toBookResponse(Book book) {
    return BookResponse.builder()
      .id(book.getId())
      .title(book.getTitle())
      .authorName(book.getAuthorName())
      .isbn(book.getIsbn())
      .synopsis(book.getSynopsis())
      .rate(book.getRate())
      .archived(book.isArchived())
      .shareable(book.isShareable())
      .owner(book.getOwner().fullName())
      // Todo : implement cover book
      //.cover()
      .build();
  }

  public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
    return BorrowedBookResponse.builder()
      .id(history.getId())
      .title(history.getBook().getTitle())
      .authorName(history.getBook().getAuthorName())
      .isbn(history.getBook().getIsbn())
      .rate(history.getBook().getRate())
      .returned(history.isReturned())
      .returnedApproved(history.isReturnApproved())
      .build();
  }
}
