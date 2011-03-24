package com.cgdecker.twitter.status;

import com.google.common.collect.ImmutableList;

/**
 * A parsed status text that has been broken down in to {@link StatusPart}s.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class ParsedStatus {
  private final ImmutableList<StatusPart> parts;

  public ParsedStatus(Iterable<StatusPart> parts) {
    this.parts = ImmutableList.copyOf(parts);
  }

  public ImmutableList<StatusPart> getParts() {
    return parts;
  }

  @Override public boolean equals(Object obj) {
    return obj instanceof ParsedStatus && parts.equals(((ParsedStatus) obj).getParts());
  }

  @Override public int hashCode() {
    return parts.hashCode();
  }

  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    for (StatusPart part : parts) {
      part.appendHtml(builder);
    }
    return builder.toString();
  }
}
