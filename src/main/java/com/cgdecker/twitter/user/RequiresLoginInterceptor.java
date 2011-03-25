package com.cgdecker.twitter.user;

import com.google.sitebricks.headless.Reply;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
// TODO: Move this somewhere under web
public class RequiresLoginInterceptor implements MethodInterceptor {
  private Provider<UserSession> userSessionProvider;

  @Inject void setUserSessionProvider(Provider<UserSession> userSessionProvider) {
    this.userSessionProvider = userSessionProvider;
  }

  @Override public Object invoke(MethodInvocation invocation) throws Throwable {
    if (!userSessionProvider.get().isLoggedIn()) {
      if (Reply.class.isAssignableFrom(invocation.getMethod().getReturnType())) {
        return Reply.saying().redirect("/login");
      }
      return "/login";
    }
    return invocation.proceed();
  }
}
