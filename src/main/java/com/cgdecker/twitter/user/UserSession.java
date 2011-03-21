package com.cgdecker.twitter.user;

import com.google.inject.Provider;
import com.google.inject.servlet.SessionScoped;

import javax.inject.Inject;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@SessionScoped
public class UserSession implements Provider<User> {
  private final Provider<UserService> userServiceProvider;
  private volatile Long userId;

  @Inject public UserSession(Provider<UserService> userServiceProvider) {
    this.userServiceProvider = userServiceProvider;
  }

  public void login(User user) {
    this.userId = user.getId();
  }

  public boolean isLoggedIn() {
    return userId != null;
  }

  @Override public User get() {
    return userId == null ? null : userServiceProvider.get().getUser(userId);
  }

  public void logout() {
    this.userId = null;
  }
}
