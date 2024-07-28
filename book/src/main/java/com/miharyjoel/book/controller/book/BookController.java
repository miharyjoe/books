package com.miharyjoel.book.controller.book;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.dto.BookResponse;
import com.miharyjoel.book.dto.PageResponse;
import com.miharyjoel.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
