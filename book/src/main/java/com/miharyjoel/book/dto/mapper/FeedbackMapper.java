package com.miharyjoel.book.dto.mapper;

import com.miharyjoel.book.dto.FeedbackRequest;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.Feedback;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
  public Feedback toFeedback(FeedbackRequest request) {
    return Feedback.builder()
      .note(request.note())
      .comment(request.comment())
      .book(Book.builder()
        .id(request.bookId())
        .archived(false)
        .shareable(false)
        .build())
      .build();
  }
}
