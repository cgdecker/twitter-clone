package com.cgdecker.twitter.common;

import org.joda.time.DateTime;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface Clock {
  DateTime now();
}
