# hetu-util-java

**Java Utils for Henkil&#246;tunnus (or Finnish Social Security Number)**

In Finland, the personal identity code / personal identification number,  Finnish: henkil&#246;tunnus (HETU), Swedish: personbeteckning, is used for identifying the citizens in many government and civilian systems.

It uses the form DDMMYYCZZZQ, where DDMMYY is the date of birth.

## Tech stack

- JDK 25 (default)
- Other supported JDK versions:
  - JDK 21
  - JDK 17

Which library version to use for your JDK version: [JDK-VERSIONS.md](JDK-VERSIONS.md).

## Usage

- Note: in `pom.xml` / `build.gradle` put required version number

### Maven

```xml
<dependency>
	<groupId>fi.ishtech.hetu</groupId>
	<artifactId>hetu-util</artifactId>
	<version>${hetu-util.version}</version>
</dependency>

```

### Gradle

```
implementation("fi.ishtech.hetu:hetu-util:${hetuUtilVersion}")
```

### Code Samples

All methods of `fi.ishtech.hetu.util.HeTuUtil` are static.

| Method | Description |
|---|---|
| `isValidByRegex(String hetu)` | Returns `true` when the value is well-formed in the `DDMMYYCZZZQ` format. Returns `false` for `null`. |
| `isValidDateOfBirth(String hetu)` | Returns `true` when the value is well-formed and its `DDMMYY` part is a valid calendar date. |
| `isValidChecksum(String hetu)` | Returns `true` when the value is well-formed, its date of birth is valid, and its check character `Q` matches. |
| `toDateOfBirth(String hetu)` | Returns the date of birth as a `LocalDate`. Throws an exception when the value is `null`, empty, or carries an unrecognised century character. |

The century is taken from the seventh character `C`: `+` for the 1800s, `-` or one of `UVWXY` for the 1900s, and one of `ABCDEF` for the 2000s.

Example:

```java
import java.time.LocalDate;

import fi.ishtech.hetu.util.HeTuUtil;

boolean isValid = HeTuUtil.isValidChecksum("010216-855Y");	// true
LocalDate dateOfBirth = HeTuUtil.toDateOfBirth("010216-855Y");	// 1916-02-01
```

## Build

This is a library; it **does not run** as a standalone application.

### Maven

#### Local Maven Build

- Build without tests

```
./mvnw clean install -DskipTests
```

- Build with Junit tests

```
./mvnw clean install
```

## Publish to Maven Central

- Deploy to Sonatype Central

  ```
  ./mvnw clean deploy -P gpg -P central-publishing
  ```

## Known Issues

See [KNOWN-ISSUES.md](KNOWN-ISSUES.md).
