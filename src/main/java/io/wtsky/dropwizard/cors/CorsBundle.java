package io.wtsky.dropwizard.cors;

import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CorsBundle<T extends Configuration> implements ConfiguredBundle<T>, CorsConfiguration<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CorsBundle.class);

  @Override
  public void run(final T configuration, final Environment environment) {
    final CorsBundleConfiguration corsConfig = getCorsConfiguration(configuration);

    if (corsConfig.getAllowedOrigins().length() == 0) {
      LOGGER.warn("CORS support disabled.  Set AllowedOrigins parameter to enable.");
      return;
    }

    // Enable CORS headers
    final FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
    corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, corsConfig.getAllowedOrigins());
    corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, corsConfig.getAllowedHeaders());
    corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, corsConfig.getAllowedMethods());
    corsFilter.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM,
        Boolean.toString(corsConfig.getChainPreflight()));
    corsFilter.setInitParameter(CrossOriginFilter.PREFLIGHT_MAX_AGE_PARAM,
        Integer.toString(corsConfig.getPreflightMaxAge()));

    // Add URL mapping
    corsFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, corsConfig.getUrlPattern());
  }

  @Override
  public void initialize(final Bootstrap<?> bootstrap) {
    // Do nothing
  }

  public abstract CorsBundleConfiguration getCorsConfiguration(T configuration);
}