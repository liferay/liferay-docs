## Developing Plugins Using Maven [](id=developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en)

"Once upon a midnight dreary, while I pondered weak and weary..."

Here's the scene--you're sitting in a luxurious armchair next to a dancing fire,
hot beverage in hand. Shadows dance on the tapestry-covered wall, and your cat
*Lenore II* is purring softly from her favorite perch atop the mantle.

"Ah, distinctly I remember it was in the bleak December..."

At least you're passing this cold December night in grand style (in front of
your computer customizing Liferay Portal, of course). 

"Eagerly I wished the morrow;--vainly I had sought to borrow 
From *Liferay* surcease of sorrow--sorrow for my last Lenore--" 

We're sorry to hear your previous cat, the original *Lenore*, has passed away.
Just take good care of Lenore II, would you? 

"And the silken sad uncertain rustling of each purple curtain
Thrilled me--filled me with *Ant*astic terrors never felt before;"

Okay now you're being melodramatic; nobody can disdain *Apache Ant* that
vehemently. What about customizing Liferay Portal using the Ant-based Plugins
SDK could make you feel sadness and terror? 

"Deep into that darkness peering, long I stood there wondering, fearing..."

We get it! You don't want to use our Ant-based Plugins SDK. Give us surcease
from the melodrama, okay? 

"Open here I flung the shutter, when, with many a flirt and flutter,
In there stepped a stately *Maven* of the saintly days of yore."

So, you'd rather use Apache Maven to develop your Liferay plugins? 

"But *Apache Maven* still beguiling all my sad soul into smiling..."

Edgar Allen Poe liked Maven too, so you're in good company. Trust us; we know.
But if your soul was made sad because you thought you had to use Liferay's
Ant-based Plugins SDK to develop your plugins, Apache Maven will make your sad
soul smile. And while you're at it, take care of Lenore II for all of us animal
lovers, would you? 

Quoth the Maven, "Let us proceed undaunted in exploration of these topics:" 

- Installing Maven 
- Using Maven Repositories
- Installing Required Liferay Artifacts 
- Using a Parent Plugin Project
- Creating Liferay Plugins with Maven
- Deploying Liferay Plugins with Maven
- Liferay Plugin Types to Develop with Maven

As an alternative to developing plugins using the Plugins SDK, you can leverage
the Apache Maven build management framework. Here's a list of some exciting
Maven features: 

- Offers a simple build process. 
- Features a project object model. 
- Has a defined project life cycle. 
- Provides a dependency management system. 

Maven's core installation is lightweight; there are core plugins for compiling
source code and creating distributions, and there is an abundance of non-core
plugins, letting you extend Maven easily for your customizations. 

Many developers are switching from Ant to Maven because it offers a common
interface for project builds. Maven's universal directory structure makes it
easier for you to understand another developer's project more quickly. With
Maven, there's a simple process to build, install, and deploy project artifacts. 

Maven uses a *project object model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. Think of `pom.xml` as a
blueprint for your entire project; it describes your project's directories,
required plugins, build sequence, and dependencies. The POM is your project's
sole descriptive declaration. Once you create the `pom.xml` file and invoke the
build process, Maven does the rest, downloading your project's inferred
dependencies and building your project artifacts. If you're not already familiar
with how Maven works, you can get familiar with Maven's project object model by
reading Sonatype's documentation for it at
[http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html). 

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--its POM. Understanding a Maven project can
be much easier than understanding an Ant-based project's various build files.
Maven forces projects to conform to a standard build process, whereas Ant
projects can be built differently from project to project. Also, Maven provides
an easy way to share artifacts (e.g., JARs, WARs, etc.) across projects via
public repositories.

There are disadvantages to using Maven. You might find the Maven project
structure too restrictive, or decide that reorganizing your projects to work
with Maven is too cumbersome. Maven is intended primarily for Java-based
projects, so it can be difficult to manage your project's non-Java source code.
Consider Maven's advantages and disadvantages, then decide how you want to
manage your projects. After you're finished reading about Maven here, you can
read an in-depth book about Maven at *Maven: The Complete Reference* by
Sonatype, Inc. at
[http://www.sonatype.com/books/mvnref-book/reference/](http://www.sonatype.com/books/mvnref-book/reference/). 

Liferay provides Maven archetypes to help you build plugins of various types,
including Liferay portlets, themes, hooks, layout templates, web plugins, and
more. You can also install and deploy Liferay artifacts to your repositories.
We'll dive into all these topics in this chapter.

"Straight I wheeled a cushioned seat in front of computer desk once more;
Then, upon the velvet falling, I betook to Maven installing..."

### Installing Maven [](id=installing-maven-liferay-portal-6-2-dev-guide-02-en)

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
`$PATH`, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

### Using Maven Repositories [](id=using-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! Maven lets you install your artifacts to your machine's
local repository and even deploy them to remote repositories; so you can share
them privately with your team or with the public for general consumption.
Your *local* repository holds your downloaded artifacts and those artifacts you
install to it. *Remote* repositories are for sharing artifacts either privately
(e.g., within your development team) or publicly. To learn more about using
artifact repositories see
[http://maven.apache.org/guides/introduction/introduction-to-repositories.html](http://maven.apache.org/guides/introduction/introduction-to-repositories.html). 

Maven also lets you configure a proxy server; it mediates your requests to
public Maven repositories and caches artifacts locally. Using a local
proxy/repository helps you build projects faster and more reliably. You want
this for two reasons: accessing remote repositories is slower, and remote
repositories are sometimes unavailable. Most Maven proxy servers can also host
private repositories that hold only your private artifacts. If you're interested
in running your repository behind a proxy, see
[http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html](http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html). 

Now that you've been introduced to Maven repositories and proxy servers, let's
consider using a repository management server to create and manage your Maven
repositories. 

#### Managing Maven Repositories [](id=managing-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. For this, you'll want Nexus OSS. It's a
Maven repository management server that facilitates creating and managing
release servers, snapshot servers, and proxy servers. Release servers hold
software that has met the software provider's criteria for planned features and
quality. Snapshot servers hold software that is in a state of development. If
you're not interested in using Nexus as a repository management server, feel
free to skip this section. 

Let's create a Maven repository using Nexus OSS. If you haven't already,
download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
instructions at
[http://www.sonatype.com/books/nexus-book/reference/\_installing\_nexus.html](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html)
to install and start it. 

To create a repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. The
    default username is `admin` with password `admin123`. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 2.20: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

    ![note](../../images/tip-pen-paper.png) **Note:** To learn more about each
    type of Nexus repository, read Sonatype's *Managing Repositories* at
    [http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3.  Enter repository properties appropriate to the access you'll provide its
    artifacts. We're installing release version artifacts into this repository,
    so specify *Release* as the repository policy. Below are examples of
    repository property values: 
    - **Repository ID:** *liferay-releases*
    - **Repository Name:** *Liferay Release Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Release*

4.  Click *Save*.

You just created a Maven repository accessible from your Nexus OSS repository
server! Congratulations! 

Let's create a Maven repository to hold snapshots of each Liferay plugin we
create. Creating a *snapshot* repository is almost identical to creating a
*release* repository. The only difference is that we'll specify *Snapshot* as
its repository policy:

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
snapshots (i.e., `liferay-snapshots`). 

Let's configure your new repository servers in your Maven environment so you can
install artifacts to them. 

#### Configuring Local Maven Settings [](id=configuring-local-maven-settings-liferay-portal-6-2-dev-guide-02-en)

Before using your repository servers and/or any repository mirrors, you must
specify them in your Maven environment settings. Your repository settings enable
Maven to find the repository and get access to it for retrieving and installing
artifacts. 

---

![note](../../images/tip-pen-paper.png) **Note:** You only need to configure a
repository server if you're installing downloaded Liferay CE/EE artifacts from
a zip file or if you want to share artifacts (e.g., Liferay artifacts and/or
your plugins) with others. If you're automatically installing Liferay CE
artifacts from the Central Repository and aren't interested in sharing
artifacts, you don't need a repository server specified in your Maven settings.

However, configuring a mirror in your Maven settings is recommended as a best
practice. Get more information on mirrors and their purpose in Maven's guide on
mirrors at
[http://maven.apache.org/guides/mini/guide-mirror-settings.html](http://maven.apache.org/guides/mini/guide-mirror-settings.html). 

---

To configure your Maven environment to access your `liferay-releases` repository
server, do the following:

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

---

![note](../../images/tip-pen-paper.png) **Note:** The username `admin` and
password `admin123` are the credentials of the default Nexus OSS administrator
account. If you changed these credentials for your Nexus server, make sure to
update `settings.xml` with these changes.

---

Now that your repositories are configured, they're ready to receive all the
Liferay Maven artifacts you'll download and the Liferay plugin artifacts you'll
create!

Now, let's install the Liferay artifacts you'll need to create your plugins.

###  Installing Required Liferay Artifacts [](id=installing-required-liferay-artifacts-liferay-portal-6-2-dev-guide-02-en)

To create Liferay plugins using Maven, you'll need the archives required by
Liferay (e.g., required JAR and WAR files). This won't be problem--Liferay
provides them as Maven artifacts. 

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

#### Installing Artifacts from a Zip File [](id=installing-artifacts-from-a-zip-file-liferay-portal-6-2-dev-guide-02-en)

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

#### Building CE Maven Artifacts from Source [](id=building-ce-maven-artifacts-from-source-liferay-portal-6-2-dev-guide-02-en)

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

#### Installing Artifacts to a Repository [](id=installing-artifacts-to-a-repository-liferay-portal-6-2-dev-guide-02-en)

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

#### Deploying Artifacts to a Repository [](id=deploying-artifacts-to-a-repository-liferay-portal-6-2-dev-guide-02-en)

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1.  Make sure you've created a `liferay-releases` repository server to hold the
    Liferay Maven artifacts. If you haven't, see the *Managing Maven
    Repositories* section for instructions. 

2.  Make sure the repository that will hold your Liferay artifacts is specified
    as a server in Maven's `settings.xml` file. If it isn't, see the
    *Configuring Local Maven Settings* section for instructions on adding an
    entry for the server. 

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

    Note, if you created a repository in Nexus OSS, as demonstrated in the
    section *Managing Maven Repositories*, you can specify that repository's ID
    and URL.

5.  To deploy to your release repository server, execute

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

To verify your artifacts are deployed, navigate to the *Repositories* page of
your Nexus OSS server and select your repository. A window appears below
displaying the Liferay artifacts now deployed to your repository.

![Figure 2.23: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

Did you know that Liferay has its own Maven repository for artifacts? Let's
learn how to install artifacts from Liferay's repository next.

#### Installing Artifacts from the Liferay Repository [](id=installing-ee-artifacts-from-the-liferay-liferay-portal-6-2-dev-guide-02-en)

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

#### Installing Artifacts from the Central Repository [](id=install-ce-artifacts-from-central-repo-liferay-portal-6-2-dev-guide-02-en)

---

![note](../../images/tip-pen-paper.png) **Important:** Currently, the *GA1*
Liferay Maven artifacts are not available in Maven's Central Repository. The
Central Repository is only synced to Liferay's *6.2.0-RC5* release. As a current
workaround to accessing Liferay's *6.2 GA1* artifacts, please reference the
*Installing Artifacts from the Liferay Repository* section for setup.

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

Now that we have our Maven artifacts set up, let's configure Liferay IDE with
Maven.
