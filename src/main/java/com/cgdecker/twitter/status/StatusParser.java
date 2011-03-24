package com.cgdecker.twitter.status;

/**
 * A parser that breaks status text down in to text, links, etc.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface StatusParser {
  /**
   * Parses the given status text.
   */
  ParsedStatus parse(String status);
}
