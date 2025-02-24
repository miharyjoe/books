package com.miharyjoel.book.controller.book;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.dto.BookResponse;
import com.miharyjoel.book.dto.BorrowedBookResponse;
import com.miharyjoel.book.dto.PageResponse;
import com.miharyjoel.book.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
@Tag(name = "Book")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public ResponseEntity<Long> saveBook(
    @Valid @RequestBody BookRequest request,
    Authentication connecetedUser
  ){
  return ResponseEntity.ok(bookService.save(request, connecetedUser));
  }

  @GetMapping("{book-id}")
  public ResponseEntity<BookResponse> findBookById(
    @PathVariable("book-id") Long bookId
  ){
    return ResponseEntity.ok(bookService.findById(bookId));
  }

  @GetMapping
  public ResponseEntity<PageResponse<BookResponse>> findAllBook(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.findAllBooks(page, size, connectedUser));
  }

  @GetMapping("owner")
  public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
  }

  @GetMapping("/borrowed")
  public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, connectedUser));
  }

  @GetMapping("/returned")
  public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, connectedUser));
  }

  @PatchMapping("/shareable/{book_id]")
  public ResponseEntity<Long> updateShareableStatus(
    @PathVariable("book_id") Long bookId,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.updateShareableStatus(bookId, connectedUser));
  }

  @PatchMapping("/archive/{book_id]")
  public ResponseEntity<Long> updateArchiveStatus(
    @PathVariable("book_id") Long bookId,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.updateArchiveStatus(bookId, connectedUser));
  }

  @PostMapping("/borrow/{book_id}")
  public ResponseEntity<Long> borrowBook(
    @PathVariable("book_id") Long bookId,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.borrowBook(bookId, connectedUser));
  }

  @PatchMapping("/borrow/return/{book_id]")
  public ResponseEntity<Long> returnBorrowBook(
    @PathVariable("book_id") Long bookId,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.returnBorrowBook(bookId, connectedUser));
  }

  @PatchMapping("/borrow/return/approve/{book_id]")
  public ResponseEntity<Long> approveReturnBorrowBook(
    @PathVariable("book_id") Long bookId,
    Authentication connectedUser
  ){
    return ResponseEntity.ok(bookService.approveReturnBorrowBook(bookId, connectedUser));
  }

  @PostMapping(value = "/cover/{book_id}", consumes = "multipart/form-data")
  public ResponseEntity<?> uploadBookCoverPicture(
    @PathVariable("book_id") Long bookId,
    @Parameter()
    @RequestPart("file") MultipartFile file,
    Authentication connectedUser
  ){
    bookService.uploadBookCoverPicture(file, connectedUser, bookId);
  return ResponseEntity.accepted().build();
  }

}
