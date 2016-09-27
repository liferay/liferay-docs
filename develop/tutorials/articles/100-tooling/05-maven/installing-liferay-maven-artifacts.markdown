# Installing Liferay Maven Artifacts

To create Liferay plugins using Maven, you'll need the archives required by
Liferay (e.g., required JAR and WAR files). This won't be a problem--Liferay
provides them as Maven artifacts. 

So how do you get the Liferay artifacts? There are several different ways you
can obtain them. The most popular way to retrieve Liferay Maven artifacts is to
install them from the Central Repository. Alternatively, if you absolutely must
have the latest Liferay pre-release, you can intall the Liferay Maven artifacts
from the Liferay Nexus source repository. This tutorial explains how to install
the Maven artifacts required for Liferay plugin development. For more
information on the Maven artifact retrieval process, see the
[Getting Started with Maven](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-maven)
tutorial.

First, consider the installation process using the Central Repository.

## Installing Artifacts from the Central Repository

Using the Central Repository to install Liferay Maven artifacts is very
straightforward and doesn't require any configurations. In fact, the first time
you use Maven to compile a Liferay plugin project, Maven automatically downloads
the required artifacts from the Central Repository into your local repository if
they're not found in your local repository or any of your configured repository
servers. You'll see it happen when you package your Liferay plugins. This
automation puts more emphasis on the development of your Liferay project and
less on managing artifacts.

You can view the published Liferay Maven artifacts on the Central Repository by
searching for *liferay maven* in the repo's Search bar. For convenience, you can
reference the available artifacts at
[http://search.maven.org/#search|ga|1|liferay maven](http://search.maven.org/#search|ga|1|liferay%20maven).
You can use the Latest Version column as a guide to see what is available for
the intended version of @product@ for which you're developing.

<!-- How would I use a different version of an artifact published to the Central
Repo if I didn't want to use the latest? -Cody -->

Sometimes, the Liferay Maven artifacts for a release are not immediately
available on Maven's Central Repository. In some cases, the Central Repository
might still be synced to a previous release of Liferay. If this occurs, download
the Liferay Maven artifacts from Liferay's repository. You'll learn how to do
this next.

## Installing Artifacts from the Liferay Repository

If you'd like to access Liferay's latest Maven artifacts, you can configure
Maven to automatically download and install them from
[Liferay's Nexus repository](https://repository.liferay.com). Once configured, 
Maven automatically downloads the required artifacts from the Liferay Maven
repository into your local repository, if they're not found in your local
repository or any of your configured repository servers. You'll see it happen
when you package your Liferay plugins. 

In order to access artifacts from the Liferay Maven repository, you'll need to
configure Maven to look for them there. First, specify the Liferay repository's
credentials in your project's parent `pom.xml` file as follows:

    <repositories>
        <repository>
            <id>liferay-ce</id>
            <name>Liferay CE</name>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases</url>
            <releases><enabled>true</enabled></releases>
            <snapshots><enabled>true</enabled></snapshots>
        </repository>
    </repositories>
	  
	<pluginRepositories>
        <pluginRepository>
            <id>liferay-ce</id>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
            <releases><enabled>true</enabled></releases>
            <snapshots><enabled>true</enabled></snapshots>
        </pluginRepository>
    </pluginRepositories>

If you've configured the Liferay Nexus repository to access pre-release Liferay
Maven artifacts, and you've already been syncing from the Central Repository,
you may need to clear out parts of your local repository to force Maven to
redownload the newer artifacts. Also, do not leave the Liferay repository
configured when publishing artifacts to Maven Central. You must comment out the
Liferay Repository credentials when publishing your artifacts.

Next, when interacting with the Liferay Repository from the command line, you'll
need to use specialized commands to access it. Use the following command to
access repo:

    mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/repositories/liferay-public-releases

<!-- You'll notice I provided the specialized command here and in the portlet
archetype example. Once everything is synced correctly and we're not solely
relying on the Liferay repo, we can remove the specialized command from the
portlet archetype exercise and only specify this way here. -Cody -->



## Deploying Artifacts to a Repository

You may find it worthwhile to share your Liferay artifacts with teammates. You
can do this by creating your own Nexus repository.

Here's how you do it:

1.  Make sure you've created a repository server to hold the Liferay Maven
    artifacts. If you haven't, see the *Managing Maven Repositories* section of
    the [Setting Up Maven](/develop/tutorials/-/knowledge_base/6-2/setting-up-maven)
    tutorial for instructions. 

2.  Make sure the repository that will hold your Liferay artifacts is specified
    as a server in Maven's `settings.xml` file. If it isn't, see the
    *Configuring Local Maven Settings* section of the 
    [Setting Up Maven](/develop/tutorials/-/knowledge_base/6-2/setting-up-maven)
    tutorial for instructions on adding an entry for the server. 

    Here's an example setting for a repository server named *liferay-releases*: 

        <servers>
            ...
            <server>
                <id>liferay-releases</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
            ...
        </servers>

3.  Navigate to the directory holding the Liferay artifacts you want to install
    to your Maven repository. If you unzipped the artifacts from a downloaded
    zip file, the artifacts are in a directory that follows the naming
    convention `liferay-portal-maven-[version]`. If you built the artifacts
    yourself, they'll be in a time-stamped directory in your `liferay-portal`
    repository directory. 

4.  Create a `build.[user name].properties` file in this directory. In the new
    properties file, specify values for the properties `lp.maven.repository.id`
    and `lp.maven.repository.url`. These refer to your repository's ID and URL,
    respectively. 

    Here are some example property values:

        lp.maven.repository.id=liferay-releases
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases

    Note: If you created a repository in Nexus, as demonstrated in the *Managing
    Maven Repositories* section of the
    [Setting Up Maven](/develop/tutorials/-/knowledge_base/6-2/setting-up-maven) tutorial, you can specify
    that repository's ID and URL. 

5.  To deploy to your release repository server, execute the following command: 

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

To verify your artifacts are deployed, navigate to the *Repositories* page of
your Nexus server and select your repository. A window appears below displaying
the Liferay artifacts now deployed to your repository.

![Figure 2: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 
