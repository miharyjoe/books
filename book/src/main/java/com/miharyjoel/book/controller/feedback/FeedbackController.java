package com.miharyjoel.book.controller.feedback;

import com.miharyjoel.book.dto.FeedbackRequest;
import com.miharyjoel.book.service.FeedbackService;
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
}
