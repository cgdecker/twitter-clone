package com.cgdecker.twitter.web.pages;

import com.cgdecker.twitter.user.UserSession;
import com.cgdecker.twitter.user.LoginInfo;
import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/login")
public class LoginPage {
  private final UserService userService;
  private final UserSession userSession;

  private final LoginInfo login = new LoginInfo();

  @Inject public LoginPage(UserService userService, UserSession userSession) {
    this.userService = userService;
    this.userSession = userSession;
  }

  @Get public Object get() {
    if (userSession.isLoggedIn())
      return IndexPage.class;
    return null;
  }

  @Post public Object login() {
    User user = userService.getUserByName(login.getUsername());
    if (user != null && user.validatePassword(login.getPassword())) {
      userSession.login(user);
      return IndexPage.class;
    }

    return LoginPage.class; // TODO: Set error welcome
  }

  public LoginInfo getLogin() {
    return login;
  }
}
