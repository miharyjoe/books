package com.miharyjoel.book.controller.feedback;

import com.miharyjoel.book.dto.FeedbackRequest;
import com.miharyjoel.book.dto.FeedbackResponse;
import com.miharyjoel.book.dto.PageResponse;
import com.miharyjoel.book.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
public class FeedbackController {

    private final FeedbackService service;

  @PostMapping
  public ResponseEntity<Long> saveFeedback(
    @Valid @RequestBody FeedbackRequest request,
    Authentication connectedUser
    ){
    return ResponseEntity.ok(service.save(request, connectedUser));
    }

  @GetMapping("/book/{book_id}")
  public ResponseEntity<PageResponse<FeedbackResponse>> findAllFeedbackByBook(
    @PathVariable("book_id") Long bookId,
    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
    Authentication connectedUser
  ){
  return ResponseEntity.ok(service.findAllFeedbackByBook(bookId, page,size,connectedUser));
  }
}
