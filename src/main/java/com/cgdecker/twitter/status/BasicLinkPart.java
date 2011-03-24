package com.cgdecker.twitter.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A basic link.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class BasicLinkPart extends LinkPart {
  private final String original;
  private final String href;
  private final String alternate;

  public BasicLinkPart(String original, String href, String alternate) {
    this.original = original;
    this.href = href;
    this.alternate = alternate;
  }

  @Override public String getOriginal() {
    return original;
  }

  @Override public String getHref() {
    return href;
  }

  @Override public String getAlternate() {
    return alternate;
  }

  static StatusPartFinder finder() {
    return Finder.INSTANCE;
  }

  private static enum Finder implements StatusPartFinder {
    INSTANCE;

    private static final Pattern PATTERN = Pattern.compile(
        "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()" +
        "<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]" +
        "{};:'\".,<>?«»“”‘’]))");

    @Override public Result findPart(TextPart text) {
      Matcher matcher = PATTERN.matcher(text.getText());
      if (matcher.find()) {
        String url = matcher.group();
        String href = url;
        if (!url.startsWith("http://") && !url.startsWith("https://"))
          href = "http://" + url;
        return Result.found(matcher.start(), matcher.end(), new BasicLinkPart(url, href, null));
      }
      return Result.notFound();
    }
  }
}
