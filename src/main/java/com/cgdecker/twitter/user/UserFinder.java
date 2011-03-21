package com.cgdecker.twitter.user;

import com.google.inject.name.Named;
import com.google.inject.persist.finder.Finder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface UserFinder {
  @Finder(query = "from User where id=?")
  User getUser(long id);

  @Finder(query = "from User where username=?")
  User getUserByName(String username);
}
