package com.cgdecker.twitter;

import com.cgdecker.twitter.user.User;
import com.cgdecker.twitter.user.UserService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class AppConfig extends GuiceServletContextListener {
  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

  // blah blah blah
  @Override protected Injector getInjector() {
    log.info("*** STARTING UP ***");

    URL developJson = Resources.getResource("develop.json");

    Stage stage = developJson == null ? Stage.PRODUCTION : Stage.DEVELOPMENT;
    Injector injector = Guice.createInjector(stage, new AppModule());

    // start the persist service since we may need to use it now
    injector.getInstance(PersistService.class).start();

    if (developJson != null) {
      setUpForDevelopment(developJson, injector);
    }
    
    return injector;
  }

  private void setUpForDevelopment(URL developJson, Injector injector) {
    log.info("*** DEVELOPMENT MODE ***");

    try {
      Gson gson = injector.getInstance(Gson.class);
      DevelopmentSettings settings = gson.fromJson(Resources.toString(developJson, Charsets.UTF_8),
          DevelopmentSettings.class);
      UserService userService = injector.getInstance(UserService.class);
      for (User user : settings.getUsers()) {
        log.info("Creating user: " + user);
        userService.saveUser(user);
      }
    } catch (Exception e) {
      log.warn("failed to read development settings", e);
    }
  }
}
