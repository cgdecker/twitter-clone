package com.cgdecker.twitter.status;

import com.google.inject.AbstractModule;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class StatusModule extends AbstractModule {
  @Override protected void configure() {
    bind(StatusService.class).to(JpaStatusService.class);
  }
}
