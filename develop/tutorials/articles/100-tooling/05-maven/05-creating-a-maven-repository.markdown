# Creating a Maven Repository [](id=creating-a-maven-repository)

You'll frequently want to share Liferay artifacts and modules with teammates or
manage your repositories using a GUI. You can do this using
[Sonatype Nexus](http://www.sonatype.org/nexus/). It's a Maven repository
management server that facilitates creating and managing release servers,
snapshot servers, and proxy servers. There are several other Maven repository
management servers you can use (for example,
[Artifactory](https://www.jfrog.com/artifactory/)), but this tutorial focuses on
Nexus.

To create a Maven repository using Nexus, download
[Nexus](https://help.sonatype.com/display/NXRM2/Download) and follow the
instructions on Nexus'
[Installation page](https://help.sonatype.com/display/NXRM2/Installing+and+Running)
to install and start it. 

To create your own repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. The
    default user name is `admin` with password `admin123`. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 1: Adding a repository to hold your Liferay artifacts is easy with Nexus.](../../../images/maven-nexus-create-repo.png)

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

You just created a Liferay Maven repository accessible from your Nexus
repository server! Congratulations! 

It's also useful to create a Maven repository to hold snapshots of each Liferay
project you create. Creating a snapshot repository is almost identical to
creating a release repository. The only difference is that you specify
*Snapshot* as its repository policy. For example, examine an example snapshot
repository's property values:

- **Repository ID:** *liferay-snapshots*
- **Repository Name:** *Liferay Snapshot Repository*
- **Provider:** *Maven2*
- **Repository Policy:** *Snapshot*

Voila! You've created a repository for your Liferay releases (i.e.,
`liferay-releases`) and Liferay snapshots (i.e., `liferay-snapshots`). To learn
how to deploy your Liferay Maven artifacts to a Nexus repository, see the
[Deploying Liferay Maven Artifacts to a Repository](/develop/tutorials/-/knowledge_base/7-1/deploying-liferay-maven-artifacts-to-a-repository)
tutorial.

Next, you'll configure your new repository servers in your Maven settings to
install artifacts to them. 

## Configuring Local Maven Settings [](id=configuring-local-maven-settings)

Before using your repository servers, you must specify them in your Maven
environment settings. Your repository settings let Maven find the repository and
retrieve and install artifacts. You can configure your local Maven settings in
the `[USER_HOME]/.m2/settings.xml` file.

You only need to configure a repository server if you're sharing artifacts
(e.g., Liferay artifacts and/or your modules) with others. If you're
automatically installing Liferay artifacts from the Central/Liferay Repository
and aren't interested in sharing artifacts, you don't need a repository server
specified in your Maven settings. You can find out more about installing
artifacts from the Central Repository or Liferay's own Nexus repository in the
[Installing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/7-1/installing-liferay-maven-artifacts)
tutorial.

To configure your Maven environment to access your `liferay-releases` and
`liferay-snapshots` repository servers, do the following:

1.  Navigate to your `[USER_HOME]/.m2/settings.xml` file. Create it if it
    doesn't yet exist. 

2.  Provide settings for your repository servers. Here are contents from a
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

The user name `admin` and password `admin123` are the credentials of the default
Nexus administrator account. If you changed these credentials for your Nexus
server, make sure to update `settings.xml` with these changes. 

Now that your repositories are configured, they're ready to receive all the
Liferay Maven artifacts you'll download and the Liferay module artifacts you'll
create!
