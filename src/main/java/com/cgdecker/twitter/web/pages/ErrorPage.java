package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.common.ErrorMessage;
import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.user.UserSession;
import com.google.common.collect.ImmutableMap;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.rendering.Decorated;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/error/:code") @Decorated
public class ErrorPage extends PageTemplate {
  // TODO: These should be injected too
  private final ImmutableMap<Integer, ErrorMessage> ERRORS = ImmutableMap.of(
      404, new ErrorMessage("Not found", "Sorry, we couldn't find what you were looking for!", null));
  private final ErrorMessage DEFAULT_ERROR = new ErrorMessage("Error!", "Oops... something went wrong!", null);

  private ErrorMessage error;

  @Inject public ErrorPage(UserSession userSession, AppMessages appMessages) {
    super(userSession, appMessages);
  }

  @Get public void get(@Named("code") int errorCode) {
    error = ERRORS.containsKey(errorCode) ? ERRORS.get(errorCode) : DEFAULT_ERROR;
  }

  @Override public String getTitle() {
    return error.getTitle();
  }

  public ErrorMessage getError() {
    return error;
  }
}
