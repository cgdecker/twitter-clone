package com.cgdecker.twitter.user;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class RequiresLoginInterceptor implements MethodInterceptor {
  private Provider<UserSession> userSessionProvider;

  @Inject void setUserSessionProvider(Provider<UserSession> userSessionProvider) {
    this.userSessionProvider = userSessionProvider;
  }

  @Override public Object invoke(MethodInvocation invocation) throws Throwable {
    if (!userSessionProvider.get().isLoggedIn())
      return "/login"; // TODO: Move this somewhere under web
    return invocation.proceed();
  }
}
