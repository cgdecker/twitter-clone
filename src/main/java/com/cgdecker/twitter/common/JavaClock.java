package com.cgdecker.twitter.common;

import org.joda.time.DateTime;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class JavaClock implements Clock {
  @Override public DateTime now() {
    return new DateTime(System.currentTimeMillis());
  }
}
