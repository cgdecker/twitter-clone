package com.cgdecker.twitter.web.actions;

import com.cgdecker.twitter.common.Clock;
import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusService;
import com.cgdecker.twitter.user.RequiresLogin;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/status") @Service
public class NewStatusAction {
  private final StatusService statusService;
  private final Clock clock;
  private final UserSession userSession;

  private final Status newStatus = new Status();

  @Inject public NewStatusAction(StatusService statusService, Clock clock, UserSession userSession) {
    this.statusService = statusService;
    this.clock = clock;
    this.userSession = userSession;
  }

  @Post @RequiresLogin public Reply<?> postStatus() {
    newStatus.setAuthor(userSession.get());
    newStatus.setPostTime(clock.now());

    if (newStatus.getText().length() > 140) {
      return Reply.saying().redirect("/"); // TODO: Error message
    }

    statusService.postStatus(newStatus);

    return Reply.saying().redirect("/");
  }

  public Status getNewStatus() {
    return newStatus;
  }
}
