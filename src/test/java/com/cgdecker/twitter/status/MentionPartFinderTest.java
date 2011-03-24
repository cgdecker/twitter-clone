package com.cgdecker.twitter.status;

import org.junit.Test;

import static com.cgdecker.twitter.status.StatusAsserts.assertFound;
import static com.cgdecker.twitter.status.StatusAsserts.assertNotFound;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class MentionPartFinderTest {
  private final StatusPartFinder finder = MentionPart.finder();

  @Test public void findAtStart() {
    assertFound("@bob 1234", finder, 1, 4, new MentionPart("bob"));
  }

  @Test public void findInMiddle() {
    assertFound("something @jane something", finder, 11, 15, new MentionPart("jane"));
  }

  @Test public void findAtEnd() {
    assertFound("something @foo", finder, 11, 14, new MentionPart("foo"));
  }

  @Test public void findWholeSegment() {
    assertFound("@abc123", finder, 1, 7, new MentionPart("abc123"));
  }

  @Test public void notFoundWithNoAt() {
    assertNotFound("abc123", finder);
  }

  @Test public void notFoundWithSpaceAfterAt() {
    assertNotFound("@ abc123", finder);
  }

  @Test public void notFoundWithInvalidUsernameCharacterAfterAt() {
    assertNotFound("@$abc123", finder);
  }

  @Test public void breaksAtInvalidUsernameCharacter() {
    assertFound("@bob: 1234", finder, 1, 4, new MentionPart("bob"));
  }
}
