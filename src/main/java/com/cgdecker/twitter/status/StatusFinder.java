package com.cgdecker.twitter.status;

import com.cgdecker.twitter.user.User;
import com.google.inject.name.Named;
import com.google.inject.persist.finder.Finder;
import com.google.inject.persist.finder.FirstResult;
import com.google.inject.persist.finder.MaxResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface StatusFinder {
  @Finder(query = "FROM Status WHERE id=?")
  Status getStatus(long id);

  @Finder(query = "FROM Status WHERE author=? ORDER BY postTime DESC", returnAs = ArrayList.class)
  List<Status> getTimelineForUser(User user, @FirstResult int firstResult, @MaxResults int maxResults);

  @Finder(query = "FROM Status WHERE author IN (:users) ORDER BY postTime DESC", returnAs = ArrayList.class)
  List<Status> getMultiUserTimeline(@Named("users") Set<User> users,
                                    @FirstResult int firstResult, @MaxResults int maxResults);
}
