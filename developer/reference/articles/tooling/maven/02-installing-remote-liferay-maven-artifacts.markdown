---
header-id: installing-remote-liferay-maven-artifacts
---

# Installing Remote Liferay Maven Artifacts

[TOC levels=1-4]

If you haven't configured your project to retrieve artifacts from a custom Maven
repository, your project will leverage the artifacts from the Central
Repository. You can view these artifacts from the
[Maven Central Repository](https://search.maven.org/) site. Use the Latest
Version column as a guide to see what's available for the version of @product@
you're developing for. 

If you'd like to access Liferay's latest released Maven artifacts, configure
Maven to use [Liferay's Nexus repository](https://repository-cdn.liferay.com)
instead. To do this, open your project's parent `pom.xml` and add this:

```xml
<repositories>
    <repository>
        <id>liferay-public-releases</id>
        <name>Liferay Public Releases</name>
        <url>https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases</url>
    </repository>
</repositories>

<pluginRepositories>
    <pluginRepository>
        <id>liferay-public-releases</id>
        <url>https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
    </pluginRepository>
</pluginRepositories>
```

The above configuration retrieves artifacts from Liferay's release repository.

If you're most interested in retrieving Liferay's latest snapshot artifacts,
follow the instructions below to configure Liferay's Nexus repository to access
them.

1.  Open your project's parent `pom.xml` and add this:

    ```xml
    <repositories>
        <repository>
            <id>liferay-public-snapshots</id>
            <name>Liferay Public Snapshots</name>
            <url>https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-snapshots</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>liferay-public-snapshots</id>
            <url>https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-snapshots/</url>
        </pluginRepository>
    </pluginRepositories>
    ```

2.  Enable your project to access snapshot artifacts by adding this code to your
    parent project's `pom.xml`:

    ```xml
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
    ```

You're now equipped to access Liferay's Maven artifacts via the

- Central Repository
- Liferay Repository (releases)
- Liferay repository (snapshots)

Great job!
