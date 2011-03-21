package com.cgdecker.twitter.user;

import com.cgdecker.twitter.common.Clock;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Transactional
class JpaUserService implements UserService {
  private final UserFinder finder;
  private final EntityManager entityManager;
  private final Clock clock;

  @Inject public JpaUserService(UserFinder finder, EntityManager entityManager, Clock clock) {
    this.finder = finder;
    this.entityManager = entityManager;
    this.clock = clock;
  }

  @Override public User getUser(long id) {
    return finder.getUser(id);
  }

  @Override public User getUserByName(String username) {
    try {
      return finder.getUserByName(username);
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override public void saveUser(User user) {
    if (user.getId() == null) {
      user.setJoinedDate(clock.now());
      entityManager.persist(user);
    }
    else
      entityManager.merge(user);
  }
}
