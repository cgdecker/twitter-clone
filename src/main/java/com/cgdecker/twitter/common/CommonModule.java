package com.cgdecker.twitter.common;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class CommonModule extends AbstractModule {
  @Override protected void configure() {
    bind(Clock.class).to(JavaClock.class);
    bind(Gson.class).toInstance(new Gson());
  }
}
