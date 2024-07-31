package com.miharyjoel.book.service;

import com.miharyjoel.book.Exception.OperationNotPermittedException;
import com.miharyjoel.book.dto.FeedbackRequest;
import com.miharyjoel.book.dto.mapper.FeedbackMapper;
import com.miharyjoel.book.model.Book;
import com.miharyjoel.book.model.Feedback;
import com.miharyjoel.book.model.User;
import com.miharyjoel.book.repository.BookRepository;
import com.miharyjoel.book.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

  private final BookRepository bookRepository;
  private final FeedbackMapper feedbackMapper;
  private final FeedbackRepository feedbackRepository;

  public Long save(FeedbackRequest request, Authentication connectedUser) {
    Book book = bookRepository.findById(request.bookId())
      .orElseThrow(() -> new EntityNotFoundException("No book found with the ID: " + request.bookId()));
    if(book.isArchived() || !book.isShareable()){
      throw new OperationNotPermittedException("You cannot give a feedback for an archived or not shareable");
    }
    User user = ((User) connectedUser.getPrincipal());
    if(Objects.equals(book.getOwner().getId(), user.getId())){
      throw new OperationNotPermittedException("You cannot give a feedback to your own books");
    }
    Feedback feedback = feedbackMapper.toFeedback(request);
    return feedbackRepository.save(feedback).getId();
  }
}
