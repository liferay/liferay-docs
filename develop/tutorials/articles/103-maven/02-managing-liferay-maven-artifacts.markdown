# Installing and Managing Liferay Maven Artifacts [](id=managing-liferay-maven-artifacts)

To create Liferay plugins using Maven, you'll need the archives required by
Liferay (e.g., required JAR and WAR files). This won't be problem--Liferay
provides them as Maven artifacts. This tutorial explains how to install the
Maven artifacts required for Liferay plugin development.

So how do you get the Liferay artifacts? The exact process depends on whether
you're building plugins for Liferay EE or Liferay CE. If you're building plugins
for Liferay EE, you'll have to install the Liferay Artifacts manually from a zip
file. You can do the same if you're building plugins for Liferay CE, but there's
a simpler option available: you can automatically install CE artifacts from the
Central Repository. Alternatively for Liferay CE, if you absolutely must have
the latest pre-release changes from the Liferay CE source repository, you can
build the Liferay CE artifacts yourself. 

This tutorial demonstrates the following three ways of installing Liferay's
published artifacts: 

- Installing Artifacts from a Zip File

- Installing Artifacts from the Liferay Repository

- Installing Artifacts from the Central Repository

First, consider the manual process of downloading and installing Liferay
artifacts from a zip file. 

## Installing Artifacts from a Zip File [](id=installing-artifacts-from-a-zip-file)

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file. The zip files provide a
means to *install* the artifacts to a Maven repository of your choice.

**Downloading a Liferay EE Artifact Zip File:**

You can download the Liferay EE artifacts package from Liferay's Customer
Portal by following these steps: 

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2.  Go to the Customer Portal by clicking your profile picture in the Dockbar
    and selecting *Customer Portal*. 

3.  Select *Liferay Portal* from the *Downloads* panel.

4.  Inside *Filter by:*, select the appropriate Liferay version in the first
    field and select the *For Developers* value in the second field.

    ![Figure 1: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5.  Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

**Downloading a Liferay CE Artifact Zip File:**

You can download Liferay CE artifacts from SourceForge by following these steps:

1.  Open your browser to *Liferay Portal* on SourceForge &rarr;
    [http://sourceforge.net/projects/lportal/files/Liferay Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Select the Liferay version for which you need Maven artifacts. For example,
    if you need Maven artifacts for Liferay Portal 6.2.0 CE GA1, select version
    *6.2.0 GA1*. 

    ![Figure 2: After selecting the Liferay version, select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3.  Select the appropriate zip file. The zip files use naming convention
    `liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. If you're interesting in building the
artifacts from Liferay's source code, please see the [Building Maven Artifacts
from Source](/develop/tutorials/-/knowledge_base/6-2/building-maven-artifacts-from-source)
tutorial. 

Once you've downloaded Liferay release artifacts as a zip file or built them
from source, you'll need to install them to your Maven repository. You'll learn
how to do that next. 

## Installing Artifacts to a Repository [](id=installing-artifacts-to-a-repository)

Follow these steps to install the Liferay release artifacts to your local Maven
repository: 

1.  If you downloaded a Liferay artifacts zip file, navigate to the
    `liferay-portal-maven-[version]`directory. This is the root directory
    extracted from the Liferay artifacts zip file. If you built the artifacts
    from source, navigate to the time-stamped directory containing the artifacts
    in your Local Liferay Portal CE source project's root directory, (e.g.,
    `liferay-portal/20121105174417071`). 

2.  To install the artifacts to your local repository, execute

        ant install

    Your console shows output from the artifacts being installed from the
    Liferay Maven package into your local repository, typically located in your
    `${USER_HOME}/.m2/repository` directory.

Your local repository now contains the Liferay artifacts required to build
Liferay plugins. Wasn't that easy?

If you want to share your Liferay artifacts with teammates, you'll have to
deploy them to a release repository server. 

## Deploying Artifacts to a Repository [](id=deploying-artifacts-to-a-repository)

You may find it worthwhile to share your Liferay artifacts with teammates.

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

![Figure 3: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

Did you know that Liferay has its own Maven repository for artifacts? Let's
learn how to install artifacts from Liferay's repository next.

## Installing Artifacts from the Liferay Repository [](id=installing-artifacts-from-the-liferay-repository)

<!-- If/when the Liferay repository becomes an EE-only artifact repository,
include "EE" in front of "Artifacts in the above heading. In addition, remove
CE references from this section. Jim --> 

If you'd like to access Liferay's CE artifacts without downloading and
installing a `.zip` file, you can configure Maven to automatically
download and install them from Liferay's own repository:
<https://repository.liferay.com>. The first time
you use Maven to compile a Liferay plugin project, Maven automatically downloads
the required artifacts from the Liferay Maven repository into your local
repository, if they're not found in your local repository or any of your
configured repository servers. You'll see it happen when you package your
Liferay plugins. 

In order to access artifacts from the Liferay Maven repository, you'll need to
configure Maven to look for them there.

First, specify the Liferay Repository's credentials in your project's parent
`pom.xml` file as follows:

    <repositories>
        <repository>
            <id>liferay-public-releases</id>
            <name>Liferay Public Releases</name>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>liferay-public-releases</id>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

+$$$

**Important:** Do not leave the Liferay repository configured when publishing
artifacts to Maven Central. You must comment out the Liferay Repository
credentials when publishing your artifacts.

$$$

Next, when interacting with the Liferay Repository, you'll need to use
specialized commands to access it. Use the following command to access the CE
repo:

    mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/repositories/liferay-public-releases

<!-- You'll notice I provided the specialized command here and in the portlet
archetype example. Once everything is synced correctly and we're not solely
relying on the Liferay repo, we can remove the specialized command from the
portlet archetype exercise and only specify this way here. -Cody -->

Liferay makes its artifacts available on Maven's Central Repository as well. As
with using Liferay's Maven repository, the Maven's Central Repository enables
you to automatically download and install Liferay Maven artifacts. Let's see
how. 

## Installing Artifacts from the Central Repository [](id=installing-artifacts-from-the-central-repository)

+$$$

**Note:** Sometimes, the Liferay Maven artifacts for a release are not
immediately available on Maven's Central Repository. The Central Repository
might still be synced to a previous release of Liferay. If this occurs, just
download the Liferay Maven artifacts from Liferay's repository. (See the
previous section for details.)

$$$

Liferay offers an option for automatic download and installation of Liferay
Maven artifacts publicly available on the *Central Repository*, located at
[http://search.maven.org/#search|ga|1|liferay maven](http://search.maven.org/#search|ga|1|liferay%20maven).
They are updated
with Liferay releases (e.g., 6.0.6, 6.1.1, 6.1.2, 6.1.20, 6.1.30, 6.2.0-RC5,
etc.). The first time you use Maven to compile a Liferay plugin project, Maven
automatically downloads the required artifacts from the Central Repository into
your local repository if they're not found in your local repository or any of
your configured repository servers. You'll see it happen when you package your
Liferay plugins. 

## Related Topics [](id=related-topics)

[Building Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/6-2/building-maven-artifacts-from-source)

