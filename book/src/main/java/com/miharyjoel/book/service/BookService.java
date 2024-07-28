package com.miharyjoel.book.service;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.dto.BookResponse;
import com.miharyjoel.book.dto.BookSpecification;
import com.miharyjoel.book.dto.PageResponse;
import com.miharyjoel.book.dto.mapper.BookMapper;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.User;
import com.miharyjoel.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.miharyjoel.book.dto.BookSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  public Long save(BookRequest request, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Book book = bookMapper.toBook(request);
    book.setOwner(user);
    return bookRepository.save(book).getId();
  }

  public BookResponse findById(Long bookId) {
    return bookRepository.findById(bookId)
      .map(bookMapper::toBookResponse)
      .orElseThrow(() -> new EntityNotFoundException("No book found with the Id : "+ bookId));
  }


  public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
    Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());
    List<BookResponse> bookResponse = books.stream()
      .map(bookMapper::toBookResponse)
      .toList();
    return new PageResponse<>(
      bookResponse,
      books.getNumber(),
      books.getSize(),
      books.getTotalElements(),
      books.getTotalPages(),
      books.isFirst(),
      books.isLast()
    );
  }

  public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
    Page<Book> books = bookRepository.findAll(withOwnerId(user.getId()), pageable);
    List<BookResponse> bookResponse = books.stream()
      .map(bookMapper::toBookResponse)
      .toList();
    return new PageResponse<>(
      bookResponse,
      books.getNumber(),
      books.getSize(),
      books.getTotalElements(),
      books.getTotalPages(),
      books.isFirst(),
      books.isLast()
    );
  }
}
