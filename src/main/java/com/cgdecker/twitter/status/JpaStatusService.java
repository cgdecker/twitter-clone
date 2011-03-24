package com.cgdecker.twitter.status;

import com.cgdecker.twitter.user.User;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.inject.persist.Transactional;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Transactional
class JpaStatusService implements StatusService {
  private final EntityManager entityManager;
  private final StatusFinder statusFinder;

  @Inject public JpaStatusService(EntityManager entityManager, StatusFinder statusFinder) {
    this.entityManager = entityManager;
    this.statusFinder = statusFinder;
  }

  @Override public void postStatus(Status status) {
    entityManager.persist(status);
  }

  @Override public void deleteStatus(long id) {
    entityManager.remove(getStatus(id));
  }

  @Override public Status getStatus(long id) {
    return statusFinder.getStatus(id);
  }

  @Override public List<Status> getTimelineForUser(User user, int firstResult, int maxResults) {
    return statusFinder.getTimelineForUser(user, firstResult, maxResults);
  }

  @Override
  public List<Status> getFollowedUserTimeline(User user, int firstResult, int maxResults) {
    return statusFinder.getMultiUserTimeline(
        Sets.union(ImmutableSet.of(user), user.getFollowing()), firstResult, maxResults);
  }
}
