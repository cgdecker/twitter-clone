package com.cgdecker.twitter.web.bricks;

import com.cgdecker.twitter.status.Status;
import com.google.sitebricks.rendering.EmbedAs;

import java.util.List;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@EmbedAs("Timeline")
public class TimelineBrick {
  private List<Status> statuses;

  public List<Status> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<Status> statuses) {
    this.statuses = statuses;
  }
}
