package com.cgdecker.twitter.web.bricks;

import com.cgdecker.twitter.status.ParsedStatus;
import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusParser;
import com.cgdecker.twitter.user.User;
import com.google.common.collect.Lists;
import com.google.sitebricks.rendering.EmbedAs;
import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@EmbedAs("Timeline")
public class TimelineBrick {
  private final StatusParser parser;

  private List<StatusView> statuses;

  @Inject public TimelineBrick(StatusParser parser) {
    this.parser = parser;
  }

  public List<StatusView> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<Status> statuses) {
    this.statuses = Lists.newArrayList();
    for (Status status : statuses) {
      this.statuses.add(new StatusView(status, parser.parse(status.getText())));
    }
  }

  public static class StatusView {
    private final Status status;
    private final ParsedStatus parsedStatus;

    public StatusView(Status status, ParsedStatus parsedStatus) {
      this.status = status;
      this.parsedStatus = parsedStatus;
    }

    public Long getId() {
      return status.getId();
    }

    public String getText() {
      return parsedStatus.toString();
    }

    public User getAuthor() {
      return status.getAuthor();
    }

    public DateTime getPostTime() {
      return status.getPostTime();
    }
  }
}
