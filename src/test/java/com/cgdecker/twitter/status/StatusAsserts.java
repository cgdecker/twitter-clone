package com.cgdecker.twitter.status;

import com.google.common.collect.ImmutableList;
import net.jcip.annotations.Immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class StatusAsserts {
  public static void assertNotFound(String text, StatusPartFinder finder) {
    assertFalse(finder.findPart(new TextPart(text)).wasFound());
  }

  public static void assertFound(String text, StatusPartFinder finder, int start, int end,
                                 StatusPart part) {
    StatusPartFinder.Result result = finder.findPart(new TextPart(text));
    assertEquals(start, result.getStart());
    assertEquals(end, result.getEnd());
    assertEquals(part, result.getPart());
  }

  public static void assertParse(String text, StatusParser parser, StatusPart... parts) {
    ImmutableList<StatusPart> expected = ImmutableList.copyOf(parts);
    ImmutableList<StatusPart> actual = parser.parse(text).getParts();
    int minSize = Math.min(expected.size(), actual.size());
    for (int i = 0; i < minSize; i++) {
      assertEquals(expected.get(i), actual.get(i));
    }
    if (minSize < expected.size()) {
      fail("parsed missing expected parts " + expected.subList(minSize, expected.size()));
    }
    if (minSize < actual.size()) {
      fail("parsed has unexpected parts " + actual.subList(minSize, actual.size()));
    }
  }
}
