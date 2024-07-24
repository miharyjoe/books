package com.miharyjoel.book.service;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.dto.mapper.BookMapper;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.User;
import com.miharyjoel.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  public Long save(BookRequest request, Authentication connecetedUser) {
    User user = ((User) connecetedUser.getPrincipal());
    Book book = bookMapper.toBook(request);
    book.setOwner(user);
    return bookRepository.save(book).getId();
  }

}
