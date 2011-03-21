package com.cgdecker.twitter.user;

import java.util.Set;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface UserService {
  User getUser(long id);

  User getUserByName(String username);

  void saveUser(User user);
}
