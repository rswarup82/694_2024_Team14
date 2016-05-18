Unified Property Configuration
==============================

The objective of this project is to load various application properties from various sources and make it available to runtime environments(JVM/servers). I started thinking about unified property management back in days when spring declared below blog post.

https://spring.io/blog/2011/02/15/spring-3-1-m1-unified-property-management/

In java based enterprise application always has requirement to load various application properties from classpath or properties file located in dev/qa/uat/production servers or system properties. Let's categories all properties,

1. Application Configuration properties - Properties are getting loaded from application configuration file. It could contains common properties whose values are static across all environments. In this project I am using application.cfg located under resource folder. Please refer to AppConfigPropertiesConfigurationSource.java

2. Classpath properties - Properties are getting loaded from application properties file which is located inside project under resource folder. In this project I am using application.properties located under resource folder. Please refer to ClasspathConfigurationSource.java

3. Environment Specific properties - Properties are getting loaded from DEV/QA/UAT/PRODUCTION servers. I presumed that there are system properties by which environmen specific properties are getting declared. In this project I am trying to locate environment specific property file location from -Dapp.conf.dir=/apps/config

In JVM classpath we can add JVM arguments and later from application code we can load property files. In this project I am using FileSystemBasedConfigurationSource.java. I presume that file system property file name is app-environment.properties. Hence load it accordingly.

4. System Properties - I think system properties can be available to application. I would like to load system properties to load at the end so that properties value should not get overriden by other property sources.

Again, sequence of loading application properties can be configured which added additional flexibilities which property source to be loaded and what all properties should be available to spring applications.