package com.miharyjoel.book.dto.mapper;

import com.miharyjoel.book.dto.FeedbackRequest;
import com.miharyjoel.book.dto.FeedbackResponse;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.Feedback;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

  public FeedbackResponse toFeedbackResponse(Feedback feedback, Long id) {
      return FeedbackResponse.builder()
        .note(feedback.getNote())
        .comment(feedback.getComment())
        .ownFeedback(Objects.equals(feedback.getCreatedBy(), id))
        .build();
  }
}
