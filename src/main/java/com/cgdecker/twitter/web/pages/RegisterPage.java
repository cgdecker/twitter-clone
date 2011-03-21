package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.common.Clock;
import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/register")
class RegisterPage {
  private final UserService userService;

  private final User user = new User();

  @Inject public RegisterPage(UserService userService) {
    this.userService = userService;
  }

  @Get public Object get(UserSession userSession) {
    if (userSession.isLoggedIn())
      return IndexPage.class;
    return null;
  }

  @Post public Object post() {
    if (!checkUser(user))
      return this;
    userService.saveUser(user);
    return IndexPage.class;
  }

  public User getUser() {
    return user;
  }

  private boolean checkUser(User user) {
    // TODO: Validate email too
    return userService.getUserByName(user.getUsername()) == null;
  }
}
