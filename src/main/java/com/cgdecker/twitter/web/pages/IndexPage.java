package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.status.Status;
import com.cgdecker.twitter.status.StatusService;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.cgdecker.twitter.web.PageBase;
import com.google.inject.persist.Transactional;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/") @Transactional
public class IndexPage extends PageBase {
  private final AppMessages messages;
  private final StatusService statusService;

  private static AtomicBoolean started = new AtomicBoolean(); // TODO: For testing... remove

  private List<Status> statuses;

  @Inject IndexPage(AppMessages messages, StatusService statusService) {
    this.messages = messages;
    this.statusService = statusService;
  }

  @Get public void get(UserService userService) {
    // TODO: For testing... remove
    if (started.compareAndSet(false, true)) {
      User colin = new User("cgdecker", "a", "Colin", "Decker", "cgdecker@gmail.com", new DateTime());
      User jessica = new User("jnruse", "a", "Jessica", "Ruse", "jnruse@gmail.com", new DateTime());
      userService.saveUser(colin);
      userService.saveUser(jessica);
    }

    if (isLoggedIn()) {
      statuses = statusService.getFollowedUserTimeline(getCurrentUser(), 0, 100);
    }
  }

  public String getWelcomeMessage() {
    return messages.welcome();
  }

  public List<Status> getStatuses() {
    return statuses;
  }
}
