package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.User;
import com.google.sitebricks.Show;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Show
public abstract class PageTemplate {
  protected final UserSession userSession;
  protected final AppMessages appMessages;

  @Inject protected PageTemplate(UserSession userSession, AppMessages appMessages) {
    this.userSession = userSession;
    this.appMessages = appMessages;
  }

  public abstract String getTitle();

  public String getAppName() {
    return appMessages.appName();
  }

  public User getCurrentUser() {
    return userSession.get();
  }

  public boolean isLoggedIn() {
    return userSession.isLoggedIn();
  }
}
