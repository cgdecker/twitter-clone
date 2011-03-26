package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.common.ErrorMessage;
import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.User;
import com.google.sitebricks.Show;
import com.google.sitebricks.headless.Reply;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Show
public abstract class PageTemplate {
  protected final UserSession userSession;
  protected final AppMessages appMessages;

  private ErrorMessage error;

  @Inject protected PageTemplate(UserSession userSession, AppMessages appMessages) {
    this.userSession = userSession;
    this.appMessages = appMessages;
  }

  public abstract String getTitle();

  public ErrorMessage getError() {
    return error;
  }

  protected void setError(ErrorMessage error) {
    this.error = error;
  }

  protected Reply<?> notFound() {
    setError(new ErrorMessage("Not found", "Sorry, we couldn't find what you were looking for!", null));
    return Reply.with(this).template(ErrorPage.class).notFound();
  }

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
