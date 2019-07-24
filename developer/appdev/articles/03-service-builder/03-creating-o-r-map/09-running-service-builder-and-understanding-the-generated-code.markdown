---
header-id: running-service-builder
---

# Running Service Builder

[TOC levels=1-4]

Here you'll learn how to run Service Builder. If want to use Service Builder in 
your application but haven't yet
[created a `service.xml` file that defines an object-relational map for you application](/docs/7-2/appdev/-/knowledge_base/a/defining-an-object-relational-map-with-service-builder),
make sure to do so before proceeding. 

Open a command line and navigate to your application folder (the folder that
contains your `*-api` and `*-service` modules). 

## Gradle 

To build your services using Gradle, enter the following command:

```bash
blade gw buildService
```

or

```bash
gradlew buildService
```

Blade's `gw` command works in any project that has a Gradle Wrapper available to
it. Projects generated using Liferay project templates have a Gradle Wrapper. 

| **Note:** Liferay Workspace's Gradle Wrapper script is in the workspace root
| folder. If your application project folder is located at
| `[workspace]/modules/[application]`, for example, the Gradle Wrapper is
| available at `../../gradlew`.

## Maven

If you're using Maven, build the services by running the following command:

```bash
mvn service-builder:build
```

**Important:** The `mvn service-builder:build` command only works if you're
using the `com.liferay.portal.tools.service.builder` plugin version 1.0.145+.
Maven projects using an earlier version of the Service Builder plugin should
update their POM accordingly. More information is available on
[using Maven to run Service Builder](/docs/7-2/reference/-/knowledge_base/r/using-service-builder-in-a-maven-project).

On successfully building the services, Service Builder prints the message `BUILD
SUCCESSFUL`. Many generated files appear in your project. They represent a model
layer, service layer, and persistence layer for your entities. Don't worry about
the number of generated files---they're explained in the next article, where you
can review the code Service Builder generates for your entities.

