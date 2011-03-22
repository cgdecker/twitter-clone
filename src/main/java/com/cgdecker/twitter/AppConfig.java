package com.cgdecker.twitter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class AppConfig extends GuiceServletContextListener {
  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

  // blah blah blah
  @Override protected Injector getInjector() {
    log.info("*** STARTING UP ***");
    return Guice.createInjector(new AppModule());
  }
}
