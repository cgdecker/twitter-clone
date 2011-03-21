package com.cgdecker.twitter.web.bricks;

import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserSession;
import com.google.sitebricks.rendering.EmbedAs;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@EmbedAs("FollowUnfollow")
public class UserFollowUnfollowBrick {
  private final UserSession userSession;
  private User user;

  public UserFollowUnfollowBrick(UserSession userSession) {
    this.userSession = userSession;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isFollowing() {
    return userSession.get().isFollowing(user);
  }

  public boolean isAllowFollowing() {
    return userSession.isLoggedIn() && !isCurrentUser();
  }

  private boolean isCurrentUser() {
    return user.equals(userSession.get());
  }
}
