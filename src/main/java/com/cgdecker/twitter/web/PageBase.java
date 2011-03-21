package com.cgdecker.twitter.web;

import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.User;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public abstract class PageBase {
  private UserSession userSession;

  @Inject void setUserSession(UserSession userSession) {
    this.userSession = userSession;
  }

  public User getCurrentUser() {
    return userSession.get();
  }

  public boolean isLoggedIn() {
    return userSession.isLoggedIn();
  }
}
