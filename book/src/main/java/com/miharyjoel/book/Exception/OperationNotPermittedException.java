package com.miharyjoel.book.Exception;

public class OperationNotPermittedException extends RuntimeException {
  public OperationNotPermittedException(String message) {
    super(message);
  }
}
