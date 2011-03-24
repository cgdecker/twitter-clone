package com.cgdecker.twitter.status;

/**
 * A part of a status, such as plaint text, a link, an @mention or a #hashtag.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface StatusPart {
  /**
   * Return an appropriate HTML representation of this part. The HTML representations of all parts
   * of a status will be concatenated together to form the final display text.
   */
  void appendHtml(StringBuilder builder);
}
