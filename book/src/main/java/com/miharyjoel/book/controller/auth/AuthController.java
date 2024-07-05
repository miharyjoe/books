package com.miharyjoel.book.controller.auth;

import com.miharyjoel.book.dto.RegistrationRequest;
import com.miharyjoel.book.service.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "authentication")
public class AuthController {

  private final AuthenticationService service;


  @PostMapping("/register")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<?> register(
    @RequestBody @Valid RegistrationRequest request
  ){
    service.register(request);
    return ResponseEntity.accepted().build();
  };
}
