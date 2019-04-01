# Maven

[Maven](https://maven.apache.org/) is a viable option for managing Liferay
projects if you don't want to use Liferay's default Gradle management system.
Liferay provides several
[Maven plugins](/developer/reference/-/knowledge_base/7-2/maven-plugins) to let you
generate and manage your project. Liferay also provides Maven artifacts that are
easy to obtain and are required for Liferay Maven module development. In this
section, you'll learn how to

- Install Liferay Maven artifacts
- Build an OSGi Module JAR with Maven
- Create/Manage a Maven Repository
- Use Service Builder in a Maven project
- Compile Sass files in a Maven project
- Build a Liferay theme with Maven

Because @product@ is tool agnostic, Maven is fully supported for @product@
development. Read on for details on some additional info on these topics. The
articles in this section cover these topics in greater detail.

## Installing Liferay Maven Artifacts

To create Liferay modules using Maven, you'll need the archives required by
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










