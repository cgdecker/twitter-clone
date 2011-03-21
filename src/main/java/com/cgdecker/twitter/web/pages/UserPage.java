package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusService;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.cgdecker.twitter.web.PageBase;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/:username") @Transactional
public class UserPage extends PageBase {
  private final UserService userService;
  private final StatusService statusService;

  private User user;
  private List<Status> statuses;

  @Inject public UserPage(UserService userService, StatusService statusService) {
    this.userService = userService;
    this.statusService = statusService;
  }

  @Get public void get(@Named("username") String username, EntityManager em) {
    user = userService.getUserByName(username);
    statuses = statusService.getTimelineForUser(user, 0, 100);
  }

  public User getUser() {
    return user;
  }

  public List<Status> getStatuses() {
    return statuses;
  }

  public Set<User> getFollowers() {
    return user.getFollowers();
  }
}
