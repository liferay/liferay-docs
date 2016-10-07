# Getting Started with Maven

Before diving into Maven-based development in Liferay, you'll need to install
[Maven](https://maven.apache.org/) on your machine and plan how you want to
manage your Maven-based plugins. You'll focus on these topics in this tutorial.

When you begin using Maven to build Liferay projects, it creates a local
repository on your machine. The default local repository typically resides in
your `[USER_HOME]/.m2` folder. When you build Liferay projects using Maven,
Maven downloads *artifacts* from the Central Repository and posts them to your
local `.m2` repository automatically. Then your Maven project references the
local repository for dependencies (artifacts), which are necessary to
successfully complete its build process. Every time a Maven project cannot
retrieve an artifact from the local repository, the configured remote repository
is accessed to download and save the missing artifact to the `.m2` repository.
The Maven lifecycle for referencing dependencies in a Liferay project is
illustrated in the figure below.

![Figure 1: Artifacts must be available from your local `.m2` repository before your Maven project can be built.](../../../images/maven-artifact-lifecycle.png)

Maven artifacts are typically JAR or WAR files, which are used to identify
dependencies needed to build your project. Maven artifacts contain an artifact
ID, group ID, and version, which are used to uniquely identify an artifact.
Required artifact dependencies should be declared in the project's `pom.xml`
file. This file is also used for many other reasons, which are explained
throughout the Liferay Maven tutorials.

When developing Liferay modules using Maven, you'll need to install and
reference the Liferay Maven artifacts provided by Liferay. You can learn how to
do this a few different ways, which are discussed in the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts)
tutorial.

+$$$

**Note:** Although the Central Repository is the primary place to acquire
Liferay Maven artifacts, you can also configure your project to obtain them from
Liferay's Nexus repository. The Liferay hosted repository holds pre-release
artifacts that may not be available from the Central Repository. Visit the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts)
tutorial for more information.

$$$

Once you install Maven and have automatically retrieved your Maven project's
dependencies, you'll need to decide if you want to share your Liferay project in
a remote repository. You can do this by creating your own Nexus repository.
Nexus is a Maven repository management server; using Nexus for Maven repository
management is convenient but not required. You'll step through a simple example
of setting up a Nexus repository later.

First, you'll go over installing Maven to your machine.

## Installing Maven

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi).
Putting your Maven installation's `bin` directory in your system's `$PATH`
facilitates running the Maven executable (`mvn`) from your command prompt. You
can check if Maven is installed by running `mvn -v` from your command prompt. If
your Maven version is returned, you've successfully installed Maven!

You'll learn some basics about Maven repositories next.

## Understanding Maven Repositories

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! Maven lets you install your artifacts both to local and
remote repositories. This means that you can share Maven repositories privately
with your team or with the public. A local repository can actually refer to
two types of Maven repositories, so both will be discussed here to avoid
confusion.

- A *local .m2* repository holds your own downloaded artifacts and the artifacts
you install to it. This is your personal repository that is automatically
referred to when packaging your Liferay Maven projects locally.
- A *local* repository is used by companies that restrict access to a public
network. Therefore, local repostories are provided on local intranets to allow
employees to use and share Maven artifacts.

*Remote* repositories are for sharing artifacts either privately (e.g., within
your development team) or publicly. These repositories require that you have 
public network access. To learn more about using artifact repositories, see
Maven's
[Introduction to Repositories](http://maven.apache.org/guides/introduction/introduction-to-repositories.html)
guide.

Maven also lets you configure a proxy server; it mediates your requests to
public Maven repositories and caches artifacts locally. Using a local
proxy/repository helps you build projects faster and more reliably. You want
this for two reasons: accessing remote repositories is slower, and remote
repositories are sometimes unavailable. Most Maven proxy servers can also host
private repositories that hold only your private artifacts. If you're interested
in running your repository behind a proxy, see Sonatype's
[Running Behind a Reverse Proxy](http://books.sonatype.com/nexus-book/reference/install-sect-proxy.html)
guide. 

Now that you've been introduced to Maven repositories and proxy servers, you may
want to consider using a repository management server to create and manage your
Maven repositories. 

## Managing Maven Repositories

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. You might find Sonatype Nexus to be a
valuable tool for managing your repositories. It's a Maven repository management
server that facilitates creating and managing release servers, snapshot servers,
and proxy servers. Release servers hold software that has met the software
provider's criteria for planned features and quality. Snapshot servers hold
software that is in a state of development. If you're not interested in using
Nexus as a repository management server, feel free to skip this section. 

To create a Maven repository using Nexus, first download
[Nexus](http://www.sonatype.org/nexus/) and follow the instructions at
[http://books.sonatype.com/nexus-book/reference/install.html](http://books.sonatype.com/nexus-book/reference/install.html)
to install and start it. If you're using Windows, you must start Nexus from a
command prompt with administrator privileges.

To create a repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. The
    default username is `admin` with password `admin123`. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 2: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../../images/maven-nexus-create-repo.png)

    To learn more about each type of Nexus repository, read Sonatype's
    [Managing Repositories](http://books.sonatype.com/nexus-book/reference/confignx-sect-manage-repo.html)
    guide.

3.  Enter repository properties appropriate for the type of artifacts it will
    hold. If you're installing release version artifacts into the repository,
    specify *Release* as the repository policy. Below are example repository
    property values: 
    - **Repository ID:** *liferay-releases*
    - **Repository Name:** *Liferay Release Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Release*

4.  Click *Save*.

You just created a Maven repository accessible from your Nexus OSS repository
server! Congratulations! 

It's also useful to create a Maven repository to hold snapshots of each Liferay
plugin you create. Creating a snapshot repository is almost identical to
creating a release repository. The only difference is that you specify
*Snapshot* as its repository policy:

1.  Go to your Nexus repository server in your web browser.

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

3.  Specify repository properties like the following:
    - **Repository ID:** *liferay-snapshots*
    - **Repository Name:** *Liferay Snapshot Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Snapshot*

4.  Click *Save*.

Voila! You not only have a repository for your Liferay releases (i.e.,
`liferay-releases`), you also have a repository for your Liferay plugin
snapshots (i.e., `liferay-snapshots`). To learn how to deploy your Liferay Maven
artifacts to a Nexus repository, see the
[Deploying Liferay Maven Artifacts to a Repository](/develop/tutorials/-/knowledge_base/7-0/deploying-liferay-maven-artifacts-to-a-repository)
tutorial.

Next, you can configure your new repository servers in your Maven environment to
enable you to install artifacts to them. 

## Configuring Local Maven Settings

Before using your repository servers and/or any repository mirrors, you must
specify them in your Maven environment settings. Your repository settings let
Maven find the repository and get access to it for retrieving and installing
artifacts. You can configure your local Maven settings in the
`[USER_HOME]/.m2/settings.xml` file.

You only need to configure a repository server if you're sharing artifacts
(e.g., Liferay artifacts and/or your plugins) with others. If you're
automatically installing Liferay artifacts from the Central/Liferay Repository
and aren't interested in sharing artifacts, you don't need a repository server
specified in your Maven settings. You can find out more about installing
artifacts from the Central Repository or Liferay's own Nexus repository in the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-0/installing-liferay-maven-artifacts)
tutorial.

Configuring a mirror in your Maven settings is recommended as a best practice.
Get more information on mirrors and their purpose in Maven's
[Mirror Settings](http://maven.apache.org/guides/mini/guide-mirror-settings.html)
guide. 

To configure your Maven environment to access your `liferay-releases` and
`liferay-snapshots` repository servers, do the following:

1.  Navigate to your `[USER_HOME]/.m2/` directory. Create that directory if it
    doesn't yet exist. 

2.  Open your `settings.xml` file. If it doesn't yet exist, create it. 

3.  Provide settings for your repository servers. Here are contents from a
    `settings.xml` file that has `liferay-releases` and `liferay-snapshots`
    repository servers configured: 

        <?xml version="1.0"?>
        <settings>
            <servers>
                <server>
                    <id>liferay-releases</id>
                    <username>admin</username>
                    <password>admin123</password>
                </server>
                <server>
                    <id>liferay-snapshots</id>
                    <username>admin</username>
                    <password>admin123</password>
                </server>
            </servers>
        </settings>

The username `admin` and password `admin123` are the credentials of the default
Nexus OSS administrator account. If you changed these credentials for your Nexus
server, make sure to update `settings.xml` with these changes. 

Now that your repositories are configured, they're ready to receive all the
Liferay Maven artifacts you'll download and the Liferay plugin artifacts you'll
create!
