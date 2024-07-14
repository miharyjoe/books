package com.miharyjoel.book.controller.auth;

import com.miharyjoel.book.dto.AuthenticationRequest;
import com.miharyjoel.book.dto.AuthenticationResponse;
import com.miharyjoel.book.dto.RegistrationRequest;
import com.miharyjoel.book.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@Tag(name = "authentication")
public class AuthController {

  private final AuthenticationService service;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<?> register(
    @Valid @RequestBody RegistrationRequest request
  ) throws MessagingException {
    service.register(request);
    return ResponseEntity.accepted().build();
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
     @Valid @RequestBody AuthenticationRequest request
  ){
    return ResponseEntity.ok(service.authenticate(request));
  };


}
