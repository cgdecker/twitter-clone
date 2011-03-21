package com.cgdecker.twitter.web.actions;

import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.RequiresLogin;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/:username/unfollow") @Service
public class UserUnfollowAction {
  private final UserService userService;
  private final UserSession userSession;

  @Inject public UserUnfollowAction(UserService userService, UserSession userSession) {
    this.userService = userService;
    this.userSession = userSession;
  }

  @Post @RequiresLogin public Reply<?> unfollow(@Named("username") String username) {
    User currentUser = userSession.get();
    User user = userService.getUserByName(username);
    currentUser.unfollowUser(user);
    userService.saveUser(currentUser);
    return Reply.saying().redirect("/" + username);
  }
}
