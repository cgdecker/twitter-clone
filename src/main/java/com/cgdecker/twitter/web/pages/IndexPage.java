package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusService;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.cgdecker.twitter.user.UserSession;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;
import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/") @Decorated
public class IndexPage extends PageTemplate {
  private final AppMessages messages;
  private final StatusService statusService;

  private List<Status> statuses;

  @Inject IndexPage(UserSession userSession, AppMessages messages, StatusService statusService) {
    super(userSession, messages);
    this.messages = messages;
    this.statusService = statusService;
  }

  @Override public String getTitle() {
    return "It's a Twitter clone!";
  }

  @Get public void get() {
    if (isLoggedIn()) {
      statuses = statusService.getFollowedUserTimeline(getCurrentUser(), 0, 100);
    }
  }

  public String getWelcomeMessage() {
    return messages.welcome();
  }

  public List<Status> getStatuses() {
    return statuses;
  }
}
