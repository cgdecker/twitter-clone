package com.cgdecker.twitter.web.actions;

import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@At("/find") @Service
public class FindUserAction {
  private String username;

  @Post public Reply<?> post() {
    return Reply.saying().redirect("/" + username);
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
