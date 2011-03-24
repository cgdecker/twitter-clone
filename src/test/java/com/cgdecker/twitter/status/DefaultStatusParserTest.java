package com.cgdecker.twitter.status;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static com.cgdecker.twitter.status.StatusAsserts.assertParse;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class DefaultStatusParserTest {
  private final DefaultStatusParser mentionOnly = new DefaultStatusParser(
      ImmutableSet.of(MentionPart.finder()));
  private final DefaultStatusParser mentionAndLink = new DefaultStatusParser(
      ImmutableSet.of(MentionPart.finder(), BasicLinkPart.finder()));

  @Test public void singleFinderAndOneMatchAtStart() {
    assertParse("@cgdecker something", mentionOnly,
        text("@"), mention("cgdecker"), text(" something"));
  }

  @Test public void singleFinderAndOneMatchInMiddle() {
    assertParse("something @cgdecker something", mentionOnly,
        text("something @"), mention("cgdecker"), text(" something"));
  }

  @Test public void singleFinderAndOneMatchAtEnd() {
    assertParse("something @cgdecker", mentionOnly,
        text("something @"), mention("cgdecker"));
  }

  @Test public void singleFinderAndMultipleMatches() {
    assertParse("@cgdecker @foobar something", mentionOnly,
        text("@"), mention("cgdecker"), text(" @"), mention("foobar"), text(" something"));
  }

  @Test public void multipleFindersOnlyOneMatching() {
    assertParse("@cgdecker something", mentionAndLink,
        text("@"), mention("cgdecker"), text(" something"));
    assertParse("something @cgdecker something", mentionAndLink,
        text("something @"), mention("cgdecker"), text(" something"));
    assertParse("something @cgdecker", mentionAndLink,
        text("something @"), mention("cgdecker"));
    assertParse("@cgdecker @foobar something", mentionAndLink,
        text("@"), mention("cgdecker"), text(" @"), mention("foobar"), text(" something"));
  }

  @Test public void multipleFindersBothMatching() {
    assertParse("@cgdecker blah blah goo.gl/asdf123", mentionAndLink,
        text("@"), mention("cgdecker"), text(" blah blah "), link("goo.gl/asdf123", "http://goo.gl/asdf123"));
    assertParse("http://www.google.com @google asdf", mentionAndLink,
        link("http://www.google.com"), text(" @"), mention("google"), text(" asdf"));
  }

  private static TextPart text(String text) {
    return new TextPart(text);
  }

  private static MentionPart mention(String username) {
    return new MentionPart(username);
  }

  private static BasicLinkPart link(String url) {
    return new BasicLinkPart(url, url, null);
  }

  private static BasicLinkPart link(String url, String href) {
    return new BasicLinkPart(url, href, null);
  }
}
