Dropwizard CORS
===================
[![Build Status](https://travis-ci.com/sjthomason/dropwizard-cors.svg?branch=master)](https://travis-ci.com/sjthomason/dropwizard-cors)
[![Maven Central](https://img.shields.io/maven-central/v/io.wtsky.dropwizard/dropwizard-cors.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/io.wtsky.dropwizard/dropwizard-cors/)
[![GitHub license](https://img.shields.io/github/license/sjthomason/dropwizard-cors.svg?style=flat-square)](https://github.com/sjthomason/dropwizard-cors/tree/master)


`dropwizard-cors` is a convenience library that enables easy configuration of [Cross-Origin Resource Sharing](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) related parameters.

Usage
-----

Your [configuration class](https://www.dropwizard.io/en/latest/manual/core.html#man-core-configuration) needs a
`CorsBundleConfiguration` instance:

```java
public class ExampleConfiguration extends Configuration {
    @Valid
    @NotNull
    private CorsBundleConfiguration cors = new CorsBundleConfiguration();

    @JsonProperty("cors")
    public CorsBundleConfiguration getCorsConfiguration() {
        return cors;
    }

    @JsonProperty("cors")
    public void setCorsConfiguration(CorsBundleConfiguration cors) {
        this.cors = cors;
    }
}
```

Then, in your application's ``initialize`` method, add a new `CorsBundle` subclass:

```java
@Override
public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
    bootstrap.addBundle(new CorsBundle<ExampleConfiguration>() {
        @Override
        public CorsBundleConfiguration getCorsConfiguration(ExampleConfiguration configuration) {
            return configuration.getCorsConfiguration();
        }
    });
}
```

Maven Artifacts
---------------

This project is available on Maven Central. To add it to your project simply add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>io.wtsky.dropwizard</groupId>
    <artifactId>dropwizard-cors</artifactId>
    <version>0.2</version>
</dependency>
```

Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/sjthomason/dropwizard-cors/issues).


License
-------

Copyright (c) 2020 Whitesky Communications, LLC

This library is licensed under the BSD 2-Clause "Simplified" License.

See the LICENSE file in this repository for the full license text.
