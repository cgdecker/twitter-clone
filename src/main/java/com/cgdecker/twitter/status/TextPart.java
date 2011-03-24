package com.cgdecker.twitter.status;

/**
 * Plain, undecorated text part of a status.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class TextPart implements StatusPart {
  private final String text;

  public TextPart(String text) {
    this.text = text;
  }

  /**
   * Gets the text of this part.
   */
  public String getText() {
    return text;
  }

  @Override public void appendHtml(StringBuilder builder) {
    builder.append(text);
  }

  @Override public boolean equals(Object obj) {
    return obj instanceof TextPart && text.equals(((TextPart) obj).text);
  }

  @Override public int hashCode() {
    return text.hashCode();
  }

  @Override public String toString() {
    return text;
  }
}
