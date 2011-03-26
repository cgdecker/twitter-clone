package com.cgdecker.twitter.common;

/**
 * Error information for display on an error page.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class ErrorMessage {
  private final String title;
  private final String message;
  private final String description;

  public ErrorMessage(String title, String message, String description) {
    this.title = title;
    this.message = message;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public String getDescription() {
    return description;
  }
}
