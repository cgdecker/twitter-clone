package com.cgdecker.twitter.web.bricks;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.User;
import com.google.sitebricks.rendering.EmbedAs;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@EmbedAs("Header")
public class HeaderBrick {
  private final AppMessages messages;
  private final UserSession userSession;

  @Inject public HeaderBrick(AppMessages messages, UserSession userSession) {
    this.messages = messages;
    this.userSession = userSession;
  }

  public boolean isLoggedIn() {
    return userSession.isLoggedIn();
  }

  public User getUser() {
    return userSession.get();
  }

  public String getAppName() {
    return messages.appName();
  }
}
