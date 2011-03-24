package com.cgdecker.twitter.status;

import java.util.List;

/**
 * Finds status parts of a particular type in text.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
interface StatusPartFinder {
  /**
   * Finds the first part in the given text and returns a result containing the new part and the
   * indices within the given text part that it replaces.
   */
  Result findPart(TextPart text);

  /**
   * A result of trying to find a status part in a text part.
   */
  static class Result {
    private static final Result NOT_FOUND = new Result(-1, -1, null);

    private final int start;
    private final int end;
    private final StatusPart part;

    private Result(int start, int end, StatusPart part) {
      this.start = start;
      this.end = end;
      this.part = part;
    }

    /**
     * Creates a new found result.
     */
    public static Result found(int start, int end, StatusPart part) {
      return new Result(start, end, part);
    }

    /**
     * Gets a result indicating no part was found.
     */
    public static Result notFound() {
      return NOT_FOUND;
    }

    /**
     * Gets whether or not this result indicates a part was found.
     */
    public boolean wasFound() {
      return part != null;
    }

    /**
     * Gets the part's start.
     */
    public int getStart() {
      return start;
    }

    /**
     * Gets the part's end.
     */
    public int getEnd() {
      return end;
    }

    /**
     * Gets the part that was found.
     */
    public StatusPart getPart() {
      return part;
    }
  }
}
