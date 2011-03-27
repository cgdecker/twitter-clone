package com.cgdecker.twitter.persist;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * A persist filter that doesn't start the persist service, allowing it to be started when the app
 * starts up instead for some initialization.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Singleton
public final class KludgePersistFilter implements Filter {
  private final UnitOfWork unitOfWork;
  private final PersistService persistService;

  @Inject
  public KludgePersistFilter(UnitOfWork unitOfWork, PersistService persistService) {
    this.unitOfWork = unitOfWork;
    this.persistService = persistService;
  }

  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void destroy() {
    persistService.stop();
  }

  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
      final FilterChain filterChain) throws IOException, ServletException {

    unitOfWork.begin();
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      unitOfWork.end();
    }
  }
}
