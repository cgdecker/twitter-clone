package com.cgdecker.twitter.status;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
class DefaultStatusParser implements StatusParser {
  private final Set<StatusPartFinder> finders;

  @Inject DefaultStatusParser(Set<StatusPartFinder> finders) {
    this.finders = finders;
  }

  /*
   * Parse the status by using each part finder in turn to break down the status in to text and
   * non-text parts. Text that has been parsed into a part of a specific type can no longer be
   * parsed by part finders that come later.
   */
  @Override public ParsedStatus parse(String status) {
    List<StatusPart> parts = Lists.<StatusPart>newArrayList(new TextPart(status));
    for (StatusPartFinder finder : finders) {
      for (int i = 0; i < parts.size(); i++) {
        StatusPart part = parts.get(i);
        if (part instanceof TextPart) {
          TextPart text = (TextPart) part;
          StatusPartFinder.Result result = finder.findPart(text);
          handleResult(parts, text.getText(), i, result);
        }
      }
    }
    return new ParsedStatus(parts);
  }

  private static void handleResult(List<StatusPart> parts, String text, int index, StatusPartFinder.Result result) {
    if (!result.wasFound())
      return;

    // remove the text part that was processed
    parts.remove(index);

    // Add parts in reverse order so they end up in the correct order
    if (result.getEnd() < text.length())
      parts.add(index, new TextPart(text.substring(result.getEnd())));

    parts.add(index, result.getPart());

    if (result.getStart() > 0)
      parts.add(index, new TextPart(text.substring(0, result.getStart())));
  }

  private static void replace(List<StatusPart> parts, int index, List<StatusPart> newParts) {
    parts.remove(index);
    for (StatusPart part : Lists.reverse(newParts)) {
      parts.add(index, part);
    }
  }
}
