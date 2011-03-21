package com.cgdecker.twitter.user;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class UserModule extends AbstractModule {
  @Override protected void configure() {
    bind(UserService.class).to(JpaUserService.class);
    bind(UserSession.class);

    RequiresLoginInterceptor requiresLoginInterceptor = new RequiresLoginInterceptor();
    requestInjection(requiresLoginInterceptor);
    bindInterceptor(Matchers.any(), Matchers.annotatedWith(RequiresLogin.class), requiresLoginInterceptor);
    bindInterceptor(Matchers.annotatedWith(RequiresLogin.class), Matchers.any(), requiresLoginInterceptor);
  }
}
