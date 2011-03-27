package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.cgdecker.twitter.user.UserSession;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.rendering.Decorated;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/settings") @Decorated
public class SettingsPage extends PageTemplate {
  private final UserService userService;

  private final User user = getCurrentUser();

  @Inject public SettingsPage(UserSession userSession, AppMessages appMessages, UserService userService) {
    super(userSession, appMessages);
    this.userService = userService;
  }

  @Override public String getTitle() {
    return "Settings";
  }

  @Get public Object get() {
    if (user == null) return "/login";
    return null;
  }

  @Post public Object post() {
    if (user == null) return "/login";
    userService.saveUser(user);
    return "/";
  }

  public User getUser() {
    return user;
  }
}
