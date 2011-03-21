package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusService;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.cgdecker.twitter.user.UserSession;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;

import java.util.List;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/:username") @Decorated
public class UserPage extends PageTemplate {
  private final UserService userService;
  private final StatusService statusService;

  private User user;
  private List<Status> statuses;

  @Inject public UserPage(UserService userService, StatusService statusService,
                          UserSession userSession, AppMessages appMessages) {
    super(userSession, appMessages);
    this.userService = userService;
    this.statusService = statusService;
  }

  @Override public String getTitle() {
    return user.getUsername();
  }

  @Get public void get(@Named("username") String username) {
    user = userService.getUserByName(username);
    statuses = statusService.getTimelineForUser(user, 0, 100);
  }

  public User getUser() {
    return user;
  }

  public List<Status> getStatuses() {
    return statuses;
  }
}
