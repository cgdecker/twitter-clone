package com.cgdecker.twitter.status;

import com.cgdecker.twitter.user.User;

import java.util.List;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface StatusService {
  void postStatus(Status status);

  void deleteStatus(long id);

  Status getStatus(long id);

  List<Status> getTimelineForUser(User user, int firstResult, int maxResults);
  
  List<Status> getFollowedUserTimeline(User user, int firstResult, int maxResults);
}
