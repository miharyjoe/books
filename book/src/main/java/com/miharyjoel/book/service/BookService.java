package com.miharyjoel.book.service;

import com.miharyjoel.book.Exception.OperationNotPermittedException;
import com.miharyjoel.book.dto.*;
import com.miharyjoel.book.dto.mapper.BookMapper;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.BookTransactionHistory;
import com.miharyjoel.book.model.User;
import com.miharyjoel.book.repository.BookRepository;
import com.miharyjoel.book.repository.BookTransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.miharyjoel.book.dto.BookSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookTransactionHistoryRepository transactionHistoryRepository;
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

  public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<BookTransactionHistory> allBorrowedBook = transactionHistoryRepository.findAllBorrowedBooks(pageable, user.getId() );
    List<BorrowedBookResponse> bookResponse = allBorrowedBook.stream()
      .map(bookMapper::toBorrowedBookResponse)
      .toList();
    return new PageResponse<>(
      bookResponse,
      allBorrowedBook.getNumber(),
      allBorrowedBook.getSize(),
      allBorrowedBook.getTotalElements(),
      allBorrowedBook.getTotalPages(),
      allBorrowedBook.isFirst(),
      allBorrowedBook.isLast()
    );
  }

  public PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int size, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<BookTransactionHistory> allBorrowedBook = transactionHistoryRepository.findAllReturnedBooks(pageable, user.getId() );
    List<BorrowedBookResponse> bookResponse = allBorrowedBook.stream()
      .map(bookMapper::toBorrowedBookResponse)
      .toList();
    return new PageResponse<>(
      bookResponse,
      allBorrowedBook.getNumber(),
      allBorrowedBook.getSize(),
      allBorrowedBook.getTotalElements(),
      allBorrowedBook.getTotalPages(),
      allBorrowedBook.isFirst(),
      allBorrowedBook.isLast()
    );
  }

  public Long updateShareableStatus(Long bookId, Authentication connectedUser) {
    Book book = bookRepository.findById(bookId)
      .orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + bookId));
    User user = ((User) connectedUser.getPrincipal());
    if(!Objects.equals(book.getOwner().getId(), user.getId())){
      throw new OperationNotPermittedException("You can not update books shareable status");
    }
    book.setShareable(!book.isShareable());
    bookRepository.save(book);
    return bookId;
  }


}
