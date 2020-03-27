package io.wtsky.dropwizard.cors;

import io.dropwizard.Configuration;

public interface CorsConfiguration<T extends Configuration> {
  CorsBundleConfiguration getCorsConfiguration(T configuration);
}