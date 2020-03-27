package io.wtsky.dropwizard.cors;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotNull;

public class CorsBundleConfiguration {

  @NotNull
  @JsonProperty
  private String allowedOrigins = "";

  @NotNull
  @JsonProperty
  private List<String> allowedHeaders = Arrays.asList("Accept", "Accept-Encoding", "Authorization", "Content-Type",
                                                      "DNT", "Origin", "User-Agent", "X-Requested-With");

  @NotNull
  @JsonProperty
  private List<String> allowedMethods = Arrays.asList("DELETE", "GET", "OPTIONS", "PATCH", "POST", "PUT");

  @NotNull
  @JsonProperty
  private boolean chainPreflight = false;

  @NotNull
  @JsonProperty
  private int preflightMaxAge = 1800;

  @NotNull
  @JsonProperty
  private String urlPattern = "/*";

  public String getAllowedOrigins() {
    return allowedOrigins;
  }

  public void setAllowedOrigins(final String allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  public String getAllowedHeaders() {
    return String.join(",", allowedHeaders);
  }

  public void setAllowedHeaders(final List<String> allowedHeaders) {
    this.allowedHeaders = allowedHeaders;
  }

  public String getAllowedMethods() {
    return String.join(",", allowedMethods);
  }

  public void setAllowedMethods(final List<String> allowedMethods) {
    this.allowedMethods = allowedMethods;
  }

  public boolean getChainPreflight() {
    return chainPreflight;
  }

  public void setChainPreflight(final boolean chainPreflight) {
    this.chainPreflight = chainPreflight;
  }

  public int getPreflightMaxAge() {
    return preflightMaxAge;
  }

  public void setPreflightMaxAge(final int preflightMaxAge) {
    this.preflightMaxAge = preflightMaxAge;
  }

  public String getUrlPattern() {
    return urlPattern;
  }

  public void setUrlPattern(final String urlPattern) {
    this.urlPattern = urlPattern;
  }
}