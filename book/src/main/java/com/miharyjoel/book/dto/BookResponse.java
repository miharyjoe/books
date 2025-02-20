package com.miharyjoel.book.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
  private Long id;
  private String title;
  private String authorName;
  private String isbn;
  private String synopsis;
  private String owner;
  private byte[] cover;
  private double rate;
  private boolean archived;
  private boolean shareable;
}
