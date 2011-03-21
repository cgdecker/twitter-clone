package com.cgdecker.twitter.web.bricks;

import com.cgdecker.twitter.user.User;
import com.google.sitebricks.rendering.EmbedAs;

import java.util.List;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@EmbedAs("UserList")
public class UserListBrick {
  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
