package com.cgdecker.twitter.status;

import com.google.common.base.Objects;

/**
 * A link part of a status.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public abstract class LinkPart implements StatusPart {
  /**
   * Gets the original text the link should show.
   */
  public abstract String getOriginal();

  /**
   * Gets the URI the link points to.
   */
  public abstract String getHref();

  /**
   * Gets the alternate text for the link.
   */
  public abstract String getAlternate();

  @Override public void appendHtml(StringBuilder builder) {
    builder.append("<a href='").append(getHref()).append("'");
    String alt = getAlternate();
    if (alt != null)
      builder.append(" alt='").append(alt).append("'");
    builder.append(">").append(getOriginal()).append("</a>");
  }

  @Override public boolean equals(Object obj) {
    if (!(obj instanceof LinkPart))
      return false;
    LinkPart other = (LinkPart) obj;
    return getOriginal().equals(other.getOriginal()) &&
        getHref().equals(other.getHref()) &&
        Objects.equal(getAlternate(), other.getAlternate());
  }

  @Override public int hashCode() {
    return Objects.hashCode(getOriginal(), getHref(), getAlternate());
  }

  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    appendHtml(builder);
    return builder.toString();
  }
}
