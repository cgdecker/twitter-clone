package com.cgdecker.twitter.web.actions;

import com.cgdecker.twitter.user.UserSession;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Get;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/logout") @Service
public class LogoutAction {
  @Get public Reply<?> logout(UserSession userSession) {
    if (userSession.isLoggedIn())
      userSession.logout();
    return Reply.saying().redirect("/");
  }
}
