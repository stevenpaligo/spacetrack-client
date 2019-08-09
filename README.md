# SpaceTrack Client
This project is a Java client for querying space data (satellites, two-line element sets, etc.) from the [Space-Track.org](https://www.space-track.org/) REST API. It uses builder objects to construct queries and automatically deserializes the results into convenient data model objects.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.stevenpaligo/spacetrack-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.stevenpaligo/spacetrack-client)
[![Javadoc](https://javadoc.io/badge/com.stevenpaligo/spacetrack-client.svg)](http://www.javadoc.io/doc/com.stevenpaligo/spacetrack-client)
[![License](https://img.shields.io/badge/license-Apache%202-blue)](https://opensource.org/licenses/Apache-2.0)



## Getting Started


### Prerequisites
SpaceTrack Client requires Java 8+ and uses [SLF4J](https://www.slf4j.org/) for logging.


### Installation
To include SpaceTrack Client in a [Maven](https://maven.apache.org/) project, add the following dependency:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.stevenpaligo</groupId>
    <artifactId>spacetrack-client</artifactId>
    <version>${spacetrack-client.version}</version>
  </dependency>
  ...
</dependencies>
```

For non-Maven projects, download the JAR from Maven's [Central Repository](http://repo1.maven.org/maven2/com/stevenpaligo/spacetrack-client/). The list of dependencies can be found in the pom.xml file (see the source on [GitHub](https://github.com/stevenpaligo/spacetrack-client))



## Usage
The following is a quick "Hello World" example of querying data. The example queries the Satellite Catalog (a.k.a. SatCat) for the International Space Station's data and prints out its apogee height.

```java {.line-numbers}
/*
   Define the credentials for the service call. SpaceTrack Client includes the `DefaultCredentialProvider`
   class which takes and stores the credentials as simple strings. If a different implementation is
   required (e.g. store an encrypted password), just implement the `CredentialProvider` interface and pass
   that to the query instead. The implementation can be anything as long as it can provide the credentials
   as they should be passed to Space-Track.org.
*/
CredentialProvider credentials = new DefaultCredentialProvider("user", "password");


/*
   Query for the International Space Station's satellite record (catalog number 25544). Start by
   instantiating the query class that represents Space-Track.org's "request class" (SatCatQuery, TleQuery,
   etc.). Next, set the credential object on the query. Then set any predicates, limits, sorting, etc. that
   define how the query functions. In this example, the query is searching for the satellite whose catalog
   number is equal to 25544. Finally, call the `execute()` method to run the query and return results as
   deserialized data model objects.
*/
List<SatCat> results = new SatCatQuery().setCredentials(credentials)
  .addPredicate(new Equal<>(SatCatQueryField.CATALOG_NUMBER, 25544)).execute();


/*
Print out the apogee height
*/
SatCat internationalSpaceStation = results.get(0);

if (internationalSpaceStation.getApogeeHeightKilometers().isPresent()) {

  long apogeeHeightKm = internationalSpaceStation.getApogeeHeightKilometers().get();
  System.out.println("The International Space Station's apogee height is: " + apogeeHeightKm + " km");

} else {

  System.out.println("The International Space Station's apogee height is: unknown");
}
```

See the JavaDoc for more information.



## Contributions

Contributions (bug reports, feature requests, etc.) are always welcome and should be coordinated through the [GitHub Issues](https://github.com/stevenpaligo/spacetrack-client/issues) system.



## Authors

* **Steven Paligo** - [stevenpaligo.com](http://stevenpaligo.com)

See also the list of [contributors](https://github.com/stevenpaligo/spacetrack-client/graphs/contributors) who participated in this project.



## License

This project is licensed under the Apache License Version 2.0. See the [license file](LICENSE) file for details.
