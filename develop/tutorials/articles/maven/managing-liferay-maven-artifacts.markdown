# Managing Liferay Maven Artifacts

To create Liferay plugins using Maven, you'll need the archives required by
Liferay (e.g., required JAR and WAR files). This won't be problem--Liferay
provides them as Maven artifacts. In this article, we explain how to install the
Maven artifacts required for Liferay plugin development.

So how do you get the Liferay artifacts? The exact process depends on whether
you're building plugins for Liferay EE or Liferay CE. If you're building plugins
for Liferay EE, you'll have to install the Liferay Artifacts manually from a zip
file. You can do the same if you're building plugins for Liferay CE, but there's
a simpler option available: you can automatically install CE artifacts
from the Central Repository. Alternatively for Liferay CE, if you absolutely
must the latest pre-release changes from our Liferay CE source repository, you
can build the Liferay CE artifacts yourself. We'll demonstrate each of these
options. 

---

![note](../../images/tip-pen-paper.png) **Note:** The EE and CE zip files are a
means to *install* the artifacts to a Maven repository of your choice. In the
next few sections, we'll demonstrate the zip file and Central Repository
installation options. 

---

Let's look at the manual process first, by downloading and installing Liferay
artifacts from a zip file. 

## Installing Artifacts from a Zip File

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file.

Let's download the Liferay EE artifacts first.

**Downloading a Liferay EE Artifact Zip File:**

You can download the Liferay EE artifacts package from Liferay's Customer
Portal. Just follow these steps: 

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2.  Go to the Customer Portal by clicking your profile picture in the Dockbar
    and selecting *Customer Portal*. 

3.  Select *Liferay Portal* from the *Downloads* panel.

4.  Inside *Filter by:*, select the appropriate Liferay version in the first
    field and select the *For Developers* value in the second field.

    ![Figure 2.21: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5.  Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

**Downloading a Liferay CE Artifact Zip File:**

You can download Liferay CE artifacts from SourceForge by following these steps:

1.  Open your browser to *Liferay Portal* on SourceForge &rarr;
    [http://sourceforge.net/projects/lportal/files/Liferay Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Select the Liferay version for which you need Maven artifacts. For example,
    if you need Maven artifacts for Liferay Portal 6.2.0 CE GA1, select version
    *6.2.0 GA1*. 

    ![Figure 2.22: After selecting the Liferay version, simply select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3.  Select the appropriate zip file. The zip files use naming convention
    `liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. We'll show you how to build the artifacts
from Liferay's source code next. 

## Building CE Maven Artifacts from Source

Downloading the Liferay Maven artifacts is useful if you're interested in using
the artifacts for a particular release. However, if you'd like to use the very
latest Liferay CE Maven artifacts, you can build them from source. To build the
latest Liferay CE Maven artifacts from source, follow these steps:

1.  Navigate to your local Liferay Portal CE source project. If you don't
    already have a local Liferay Portal CE source project on your machine, you
    can fork the Liferay Portal CE Github repository, found at
    [http://github.com/liferay/liferay-portal](http://github.com/liferay/liferay-portal),
    and clone it your machine.

2.  Create an `app.server.[user name].properties` file in your local Liferay
    Portal CE source project root directory. Specify the following properties in
    it:

        app.server.type=[your application server's type. Lookup your app
        server's type in the app.server.properties file in the same directory.]
        app.server.parent.dir=[your application server's parent directory]
        app.server.[type].dir=[your application server's directory]

    For example, if you're using Liferay with Apache Tomcat 7.0.42 bundled
    in your `c:/bundles` folder, you'd specify the following
    properties:

        app.server.type=tomcat
        app.server.parent.dir=c:/liferay-portal-6.2
        app.server.tomcat.dir=${app.server.parent.dir}/tomcat-7.0.42

    Of course, you should specify the values appropriate to your application
    server and your bundle/parent directory. Note that your
    `app.server.[type].dir` directory doesn't need to exist yet; it is created
    by invoking an Ant target in the next step. 

3.  Run `ant -f build-dist.xml unzip-[app server name]` to unzip a copy of your
    preferred application server to the specified directory.

    For example, to unzip Apache Tomcat to the directory specified by your
    `app.server.tomcat.dir` property, run:
   
        ant -f build-dist.xml unzip-tomcat

4.  Create a `releases.[user name].properties` in your local Liferay Portal CE
    source project root directory and specify the following properties:

        gpg.keyname=[GPG key name]
        gpg.passphrase=[GPG passphrase]
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-snapshots
        lp.maven.repository.id=liferay-snapshots

    Of course, replace the values specified above with your own GPG and Maven
    repository credentials.

    If you don't have GPG installed and don't have a public/private GPG key, you
    should visit [http://www.gnupg.org](http://www.gnupg.org) and install GPG
    before continuing. Once you've installed GPG, generate a GPG key by running
    `gpg --gen-key` and following the instructions. Once you've generated a GPG
    key, you can find your GPG keyname by running `gpg --list-keys`.
    
    ---

    ![note](../../images/tip-pen-paper.png) **Note**: The `releases.[user
    name].properties` is not required if you only plan to install the Liferay
    artifacts locally and not deploy them. 

    ---

5.  Open a command prompt, navigate to your Liferay home directory, and build
    the Liferay Portal WAR file by running 

        ant -f build-dist.xml all zip-portal-war

6.  Deploy the Liferay artifacts to your Maven repository by running 

        ant -f build-maven.xml deploy-artifacts

    If you want the Liferay artifacts to be installed locally but don't have a
    remote Maven repository or don't want the artifacts to be remotely deployed,
    you can run the install target instead of the deploy
    target: `ant -f build-maven.xml install-artifacts`. The target installs the
    Liferay artifacts you built to your local .m2 repository (e.g., to your
    `[USER_HOME]/.m2/` directory). 

---

![note](../../images/tip-pen-paper.png) **Warning:** During the process of
 packaging up the `javadoc.jar` files for your Liferay artifacts, your machine
 may experience sluggish performance or an insufficient amount of Java heap
 space. There are two solutions to this problem:
 
- *Increase the memory available for the Javadoc packaging process:*
  Navigate to `[Liferay home]/build.xml` and search for the `javadoc` target.
  Find the `maxmemory` property and increase it as desired.
- *Skip the Javadoc packaging process:* Navigate to
  `[Liferay home]/build-maven.xml` and find the `prepare-maven` target. Within
  this target, comment out the call to the `jar-javadoc` target, like below:

		<!-- <antcall target="jar-javadoc" /> -->
		
---

Great! You now know how to build Liferay CE artifacts from your local portal
source tree. As an alternative to building the artifacts, you may have
downloaded Liferay release artifacts as a zip file. Once you've downloaded
them, you'll need to install them to your Maven repository. We'll show you how
to do that in the next section. 

## Installing Artifacts to a Repository

Let's install the Liferay release artifacts to your local Maven repository. 

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

## Deploying Artifacts to a Repository

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1.  Make sure you've created a repository server to hold the Liferay Maven
    artifacts. If you haven't, see the *Managing Maven Repositories* section of
    the *Developing Plugins Using Maven* article for instructions. 

2.  Make sure the repository that will hold your Liferay artifacts is specified
    as a server in Maven's `settings.xml` file. If it isn't, see the
    *Configuring Local Maven Settings* section of the *Developing Plugins Using
    Maven* article for instructions on adding an entry for the server. 

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
    Maven Repositories* section of the *Developing Plugins Using Maven* article,
    you can specify that repository's ID and URL.

5.  To deploy to your release repository server, execute

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

To verify your artifacts are deployed, navigate to the *Repositories* page of
your Nexus server and select your repository. A window appears below displaying
the Liferay artifacts now deployed to your repository.

![Figure 2.23: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

Did you know that Liferay has its own Maven repository for artifacts? Let's
learn how to install artifacts from Liferay's repository next.

## Installing Artifacts from the Liferay Repository

<!-- If/when the Liferay repository becomes an EE-only artifact repository,
include "EE" in front of "Artifacts in the above heading. In addition, remove
CE references from this section. Jim --> 

If you'd like to access Liferay's CE artifacts without downloading and
installing a `.zip` file, you can configure Maven to automatically
download and install them from Liferay's own repository:
[https://repository.liferay.com](https://repository.liferay.com). The first time
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
            <id>liferay-ce</id>
		    <name>Liferay CE</name>
		    <url>https://repository.liferay.com/nexus/content/groups/liferay-ce</url>
		    <releases><enabled>true</enabled></releases>
		    <snapshots><enabled>true</enabled></snapshots>
        </repository>
    </repositories>
	  
	<pluginRepositories>
        <pluginRepository>
            <id>liferay-ce</id>
            <url>https://repository.liferay.com/nexus/content/groups/liferay-ce/</url>
            <releases><enabled>true</enabled></releases>
            <snapshots><enabled>true</enabled></snapshots>
        </pluginRepository>
    </pluginRepositories>

---

![note](../../images/tip-pen-paper.png) **Important:** Do not leave the Liferay
repository configured when publishing artifacts to Maven Central. You must
comment out the Liferay Repository credentials when publishing your artifacts.

--- 

Next, when interacting with the Liferay Repository, you'll need to use
specialized commands to access it. Use the following command to access the CE
repo:

    mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/groups/liferay-ce

<!-- You'll notice I provided the specialized command here and in the portlet
archetype example. Once everything is synced correctly and we're not solely
relying on the Liferay repo, we can remove the specialized command from the
portlet archetype exercise and only specify this way here. -Cody -->

Liferay makes its artifacts available on Maven's Central Repository as well. As
with using Liferay's Maven repository, the Maven's Central Repository enables
you to automatically download and install Liferay Maven artifacts. Let's see
how. 

## Installing Artifacts from the Central Repository

---

![note](../../images/tip-pen-paper.png) **Note:** Sometimes, the Liferay Maven
artifacts for a release are not immediately available on Maven's Central
Repository. The Central Repository might still be synced to a previous release
of Liferay. If this occurs, just download the Liferay Maven artifacts from
Liferay's repository. (See the previous section for details.)

---

Liferay offers an option for automatic download and installation of Liferay
Maven artifacts publicly available on the *Central Repository*, located at
[http://search.maven.org/#search|ga|1|liferay
maven](http://search.maven.org/#search|ga|1|liferay%20maven). They are updated
with Liferay releases (e.g., 6.0.6, 6.1.1, 6.1.2, 6.1.20, 6.1.30, 6.2.0-RC5,
etc.). The first time you use Maven to compile a Liferay plugin project, Maven
automatically downloads the required artifacts from the Central Repository into
your local repository if they're not found in your local repository or any of
your configured repository servers. You'll see it happen when you package your
Liferay plugins. 

## Next Steps

Now that we have our Maven artifacts set up, let's learn how to use Maven from
Liferay IDE.

[Using Maven from Liferay IDE](http://www.liferay.com)
