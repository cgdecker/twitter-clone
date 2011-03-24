package com.cgdecker.twitter.status;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.cgdecker.twitter.status.StatusAsserts.assertFound;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class BasicLinkPartFinderTest {
  private static final String BASIC_HTTP = "http://www.google.com";
  private static final String LONGER_HTTP = "http://www.google.com/webfonts/family?family=Cabin&subset=latin";
  private static final String BITLY_HTTP = "http://bit.ly/gl6xoG";

  private static final String BASIC_NO_HTTP = "www.google.com";
  private static final String LONGER_NO_HTTP = "www.google.com/webfonts/family?family=Cabin&subset=latin";
  private static final String BITLY_NO_HTTP = "bit.ly/gl6xoG";

  private static final ImmutableList<String> HTTP_URLS = ImmutableList.of(BASIC_HTTP, LONGER_HTTP,
      BITLY_HTTP);
  private static final ImmutableList<String> NO_HTTP_URLS = ImmutableList.of(BASIC_NO_HTTP,
      LONGER_NO_HTTP, BITLY_NO_HTTP);

  @Test public void findSinglePartHttp() {
    for (String url : HTTP_URLS) {
      assertFound(url, BasicLinkPart.finder(), 0, url.length(), new BasicLinkPart(url, url, null));
    }
  }

  @Test public void findSinglePartAtStartHttp() {
    for (String url : HTTP_URLS) {
      assertFound(url + " blah blah blah", BasicLinkPart.finder(), 0, url.length(),
          new BasicLinkPart(url, url, null));
    }
  }

  @Test public void findSinglePartInMiddleHttp() {
    for (String url : HTTP_URLS) {
      assertFound("blah blah " + url + " blah blah blah", BasicLinkPart.finder(),
          10, 10 + url.length(), new BasicLinkPart(url, url, null));
    }
  }

  @Test public void findSinglePartAtEndHttp() {
    for (String url : HTTP_URLS) {
      assertFound("blah blah " + url, BasicLinkPart.finder(), 10, 10 + url.length(),
          new BasicLinkPart(url, url, null));
    }
  }

  @Test public void findSinglePartNoHttp() {
    for (String url : NO_HTTP_URLS) {
      assertFound(url, BasicLinkPart.finder(), 0, url.length(), new BasicLinkPart(url, "http://" + url, null));
    }
  }

  @Test public void findSinglePartAtStartNoHttp() {
    for (String url : NO_HTTP_URLS) {
      assertFound(url + " blah blah blah", BasicLinkPart.finder(), 0, url.length(),
          new BasicLinkPart(url, "http://" + url, null));
    }
  }

  @Test public void findSinglePartInMiddleNoHttp() {
    for (String url : NO_HTTP_URLS) {
      assertFound("blah blah " + url + " blah blah blah", BasicLinkPart.finder(),
          10, 10 + url.length(), new BasicLinkPart(url, "http://" + url, null));
    }
  }

  @Test public void findSinglePartAtEndNoHttp() {
    for (String url : NO_HTTP_URLS) {
      assertFound("blah blah " + url, BasicLinkPart.finder(), 10, 10 + url.length(),
          new BasicLinkPart(url, "http://" + url, null));
    }
  }
}
