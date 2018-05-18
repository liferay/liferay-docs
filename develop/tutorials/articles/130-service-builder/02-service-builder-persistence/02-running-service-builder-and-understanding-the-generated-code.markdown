# Running Service Builder [](id=running-service-builder)

This tutorial demonstrates how to run Service Builder. If want to use Service
Builder in your application but haven't yet
[created a `service.xml` file that defines an object-relational map for you application](/develop/tutorials/-/knowledge_base/7-1/defining-an-object-relational-map-with-service-builder),
make sure to do so before proceeding with this tutorial.

There are two ways to build services from a `service.xml` file:

- [Liferay @ide@](#using-liferay-ide-or-developer-studio)
- [Command line](#using-the-command-line) 

Liferay @ide@ is demonstrated first. 

## Using Liferay @ide@ [](id=using-liferay-ide-or-developer-studio)

From the Package Explorer, right-click on your service module and then select
*Liferay* &rarr; *build-service*. 

![Figure 1: Liferay @ide@ facilitates building Service Builder services via the *build-service* option in the service module's *Liferay* submenu.](../../../images/service-builder-ide-build-service.png)

Service Builder generates plenty of files. You can run Service Builder from the
command line too. 

## Using the Command Line [](id=using-the-command-line)

Open a command line and navigate to your application folder (the folder that
contains your `*-api` and `*-service` modules). 

To build your services using Gradle, enter the following command:

    blade gw buildService  

or

    gradlew buildService

Blade's `gw` command works in any project that has a Gradle Wrapper available to
it--projects generated using Liferay project templates have a Gradle Wrapper. 

+$$$

**Note:** Liferay Workspace's Gradle Wrapper script is in the workspace root 
folder. If your application project folder is located at
`[workspace]/modules/[application]`, for example, the Gradle Wrapper is
available at `../../gradlew`. 

$$$

If you're using Maven, build the services by running the following command:

    mvn service-builder:build

**Important:** The `mvn service-builder:build` command only works if you're
using the `com.liferay.portal.tools.service.builder` plugin version 1.0.145+.
Maven projects using an earlier version of the Service Builder plugin should
update their POM accordingly. More information is available on
[using Maven to run Service Builder](/develop/tutorials/-/knowledge_base/7-1/using-service-builder-in-a-maven-project).

On successfully building the services, Service Builder prints the message `BUILD
SUCCESSFUL`. Many generated files appear in your project. They represent a model
layer, service layer, and persistence layer for your entities. Don't worry about
the number of generated files--they're explained in the next tutorial. Its time
to review the code Service Builder generates for your entities.

