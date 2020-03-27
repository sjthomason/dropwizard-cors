package io.wtsky.dropwizard.cors;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.util.Resources;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.validation.BaseValidator;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

public class CorsTest {

  private final ObjectMapper objectMapper = Jackson.newObjectMapper();
  private final YamlConfigurationFactory<CorsBundleConfiguration> factory = new YamlConfigurationFactory<>(
          CorsBundleConfiguration.class,
          BaseValidator.newValidator(),
          objectMapper, "dw");

  private CorsBundleConfiguration config;

  @BeforeEach
  public void setUp() throws Exception {
    config = factory.build(new File(Resources.getResource("yaml/cors.yml").toURI()));
  }

  @Test
  public void hasAllowedOrigins() throws Exception {
    assertThat(config.getAllowedOrigins()).isEqualTo("*");
  }

  @Test
  public void hasAllowedHeaders() throws Exception {
    assertThat(config.getAllowedHeaders()).isEqualTo(
        "Accept,Accept-Encoding,Authorization,Content-Type,Origin,User-Agent,X-Requested-With");
  }

  @Test
  public void hasAllowedMethods() throws Exception {
    assertThat(config.getAllowedMethods()).isEqualTo("DELETE,GET,OPTIONS,POST,PUT");
  }

  @Test
  public void hasChainPreflight() throws Exception {
    assertThat(config.getChainPreflight()).isEqualTo(true);
  }

  @Test
  public void hasPreflightMaxAge() throws Exception {
    assertThat(config.getPreflightMaxAge()).isEqualTo(3600);
  }

  @Test
  public void hasUrlPattern() throws Exception {
    assertThat(config.getUrlPattern()).isEqualTo("/resources/*");
  }

}
