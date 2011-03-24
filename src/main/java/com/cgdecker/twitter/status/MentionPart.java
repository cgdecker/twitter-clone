package com.cgdecker.twitter.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An @Mention link.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class MentionPart extends LinkPart {
  private final String username;

  public MentionPart(String username) {
    this.username = username;
  }

  @Override public String getOriginal() {
    return username;
  }

  @Override public String getHref() {
    return "/" + username.toLowerCase();
  }

  @Override public String getAlternate() {
    return null;
  }

  /**
   * Gets the {@link StatusPartFinder} for mentions.
   */
  static StatusPartFinder finder() {
    return Finder.INSTANCE;
  }

  private static enum Finder implements StatusPartFinder {
    INSTANCE;

    private static final Pattern PATTERN = Pattern.compile("@([a-zA-Z0-9]+)");

    @Override public Result findPart(TextPart text) {
      Matcher matcher = PATTERN.matcher(text.getText());
      if (matcher.find()) {
        int start = matcher.start() + 1;
        int end = matcher.end();
        String username = text.getText().substring(start, end);
        return Result.found(start, end, new MentionPart(username));
      }
      return Result.notFound();
    }
  }
}
