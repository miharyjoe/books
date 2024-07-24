package com.miharyjoel.book.controller.book;

import com.miharyjoel.book.dto.BookRequest;
import com.miharyjoel.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
