package com.cgdecker.twitter.status;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class StatusModule extends AbstractModule {
  @Override protected void configure() {
    bind(StatusService.class).to(JpaStatusService.class);

    Multibinder<StatusPartFinder> finders = Multibinder.newSetBinder(binder(), StatusPartFinder.class);
    finders.addBinding().toInstance(MentionPart.finder());
    finders.addBinding().toInstance(BasicLinkPart.finder());

    bind(StatusParser.class).to(DefaultStatusParser.class);
  }
}
