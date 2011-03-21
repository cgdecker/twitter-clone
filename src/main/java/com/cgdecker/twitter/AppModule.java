package com.cgdecker.twitter;

import com.cgdecker.twitter.common.CommonModule;
import com.cgdecker.twitter.messages.AppMessages;
import com.cgdecker.twitter.status.StatusFinder;
import com.cgdecker.twitter.status.StatusModule;
import com.cgdecker.twitter.user.UserFinder;
import com.cgdecker.twitter.user.UserModule;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.sitebricks.SitebricksModule;
import com.google.sitebricks.SitebricksServletModule;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
class AppModule extends SitebricksModule {
  @Override protected SitebricksServletModule servletModule() {
    return new SitebricksServletModule() {
      @Override protected void configurePreFilters() {
        install(new JpaPersistModule("hsqldb")
            .addFinder(UserFinder.class)
            .addFinder(StatusFinder.class));
        filter("/*").through(PersistFilter.class);
      }
    };
  }

  @Override protected void configureSitebricks() {
    scan(getClass().getPackage());
    localize(AppMessages.class).usingDefault();

    install(new CommonModule());
    install(new UserModule());
    install(new StatusModule());
  }
}
