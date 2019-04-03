# Maven

[Maven](https://maven.apache.org/) is a viable option for managing Liferay
projects if you don't want to use Liferay's default Gradle management system.
Liferay provides several
[Maven plugins](/developer/reference/-/knowledge_base/7-2/maven-plugins) to let you
generate and manage your project. Liferay also provides Maven artifacts that are
easy to obtain and are required for Liferay Maven module development. In this
section, you'll learn how to

- Install Liferay Maven artifacts
- Create/Manage a Maven Repository
- Apply Maven plugins

Because @product@ is tool agnostic, Maven is fully supported for @product@
development. Read on for details about these topics.

## Installing Liferay Maven Artifacts

To create Liferay projects using Maven, you'll need the archives required by
Liferay (e.g., JAR and WAR files). This isn't a problem--Liferay provides
them as Maven artifacts. 

You can retrieve Liferay Maven artifacts two ways: 

- Install them from a remote repository. 
- Download a Liferay-provided utility for putting artifacts in local
  repositories. 

There are two remote repositories that contain Liferay artifacts: Central
Repository and Liferay Repository. The Central Repository is the default
repository used to download artifacts if you don't have a remote repository
configured. Using the Central Repository to install Liferay Maven artifacts only
requires that you 
[specify your module's dependencies](/developer/customization/-/knowledge_base/7-2/configuring-dependencies) 
in its `pom.xml` file. 

When packaging your module, the automatic Maven artifact installation process
only downloads the artifacts necessary for that module from the Central
Repository. 

The Central Repository *usually* offers the latest Liferay Maven artifacts, but
using the Liferay Repository *guarantees* the latest artifacts released by
Liferay. Other than a slight delay in artifact releases between the two
repositories, they're identical. When the Liferay repository is configured in
your `settings.xml` file, archetypes are generated based on that repository's
contents. The Liferay Maven repository offers a good alternative for those who
want the most up-to-date Maven artifacts produced by Liferay. 

| **Note:** If you've configured the Liferay Nexus repository to access Liferay
| Maven artifacts and you've already been syncing from the Central Repository,
| you may need to clear out parts of your local repository to force Maven to
| re-download the newer artifacts. Also, don't leave the Liferay repository
| enabled when publishing artifacts to Maven Central. You must comment out the
| Liferay Repository credentials when publishing your artifacts.

If you can't access remote repositories (e.g., restrictions due to a corporate
firewall), you can still install Liferay Maven artifacts from a local
repository (as a ZIP file).

## Managing Maven Artifacts in a Repository

You'll frequently want to share Liferay artifacts and modules with teammates or
manage your repositories using a GUI. You can do this using
[Sonatype Nexus](http://www.sonatype.org/nexus/). It's a Maven repository
management server that facilitates creating and managing release servers,
snapshot servers, and proxy servers. There are several other Maven repository
management servers you can use (for example,
[Artifactory](https://www.jfrog.com/artifactory/)), but this section focuses on
Nexus.

You'll learn how to

- [Create a repository](/developer/reference/-/knowledge_base/7-2/creating-a-maven-repository)
- [Configure a repository](/developer/reference/-/knowledge_base/7-2/configuring-local-maven-settings-to-access-repositories)
- [Deploy artifacts to a repository](/developer/reference/-/knowledge_base/7-2/deploying-liferay-maven-artifacts-to-a-repository)

Before using a repository server, you must specify them in your Maven
environment settings. Your repository settings let Maven find the repository and
retrieve and install artifacts. You can configure your local Maven settings in
the `[USER_HOME]/.m2/settings.xml` file.

| **Note**: You only need to configure a repository server if you're sharing
| artifacts (e.g., Liferay artifacts and/or your modules) with others. If you're
| automatically installing Liferay artifacts from the Central/Liferay Repository
| and aren't interested in sharing artifacts, you don't need a repository server
| specified in your Maven settings. You can find out more about installing
| artifacts from the Central Repository or Liferay's own Nexus repository in the
| [Installing Remote Liferay Maven Artifacts](/developer/reference/-/knowledge_base/7-2/installing-remote-liferay-maven-artifacts)
| article.

To deploy to a remote repository, your Liferay project should be packaged using
Maven. Maven provides a packaging command that creates an artifact (JAR) that
can be easily deployed to your remote repository.

Once you've created a deployable artifact, you can configure your module project
to communicate with your remote repository and use Maven's `deploy` command to
send it on its way. Once your module project resides on the remote repository,
other developers can configure your remote repository in their projects and set
dependencies in their project POMs to reference it.

## Applying Maven Plugins

There are several important Maven plugins that provide important functionality
to Liferay Maven projects. The available Liferay Maven plugins are available in
the [Maven Plugins](/developer/reference/-/knowledge_base/7-2/maven-plugins)
section.

Applying the following functionalities is covered in this section:

- [Building an OSGi module JAR](/developer/reference/-/knowledge_base/7-2/building-an-osgi-module-jar-with-maven)
- [Building themes](/developer/reference/-/knowledge_base/7-2/building-a-theme-with-maven)
- [Compiling Sass files](/developer/reference/-/knowledge_base/7-2/compiling-sass-files-in-a-maven-project)
- [Using Service Builder](/developer/reference/-/knowledge_base/7-2/using-service-builder-in-a-maven-project)

Read on to learn more!
