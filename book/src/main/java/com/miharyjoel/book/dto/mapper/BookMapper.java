package com.miharyjoel.book.dto.mapper;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.model.Book;
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
}
