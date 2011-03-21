package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;

import java.util.Set;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/:username/following")
public class UserFollowingPage {
  private final UserService userService;

  private User user;

  @Inject public UserFollowingPage(UserService userService) {
    this.userService = userService;
  }

  @Get public void get(@Named("username") String username) {
    user = userService.getUserByName(username);
  }

  public User getUser() {
    return user;
  }

  public Set<User> getFollowedUsers() {
    return user.getFollowedUsers();
  }
}
