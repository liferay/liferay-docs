# Developing Plugins Using Maven [](id=developing-plugins-using-maven)

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

## Installing Maven [](id=installing-maven)

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
path, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

## Using Maven repositories [](id=using-maven-repositories)

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

### Managing Maven Repositories [](id=managing-maven-repositories)

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. For this, you'll want Nexus OSS. It's a
Maven repository management server that facilitates creating and managing
release servers, snapshot servers, and proxy servers. If you're not interested
in using Nexus as a repository management server, feel free to skip this
section.

Let's create a Maven repository using Nexus OSS. If you haven't already,
download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
instructions at
[http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html)
to install and start it. 

To create a repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 9.1: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

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

<!-- What is a snapshot? Sounds like Maven terminology that the reader might not
know. -->

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
install artifacts to the them. 

### Configuring Local Maven Settings [](id=configuring-local-maven-settings)

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

1.  Navigate to your `${USER_HOME}/.m2/` directory.

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

##  Installing Required Liferay Artifacts [](id=installing-required-liferay-artifacts)

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

### Installing EE/CE Artifacts from a Zip File [](id=installing-artifacts-from-a-zip-file)

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file. Let's download the
Liferay EE artifacts first.

You can download the Liferay EE artifacts package from Liferay's Customer
Portal. Just follow these steps: 

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2.  Go to *Places* &rarr; *Customer Portal*.

3.  Select *Liferay Portal* from the *Downloads* panel.

4.  Inside *Filter by:*, select the appropriate Liferay version in the first
    field and select the *Development* value in the second field.

    ![Figure 9.2: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5.  Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

Get the artifacts for Liferay CE from SourceForge by following these steps:

1.  Open your browser to *Liferay Portal* on SourceForge &rarr;
    [http://sourceforge.net/projects/lportal/files/Liferay%20Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Select the Liferay version for which you need Maven artifacts. For example,
    if you need Maven artifacts for Liferay Portal 6.1.2 CE GA3, select version
    *6.1.2 GA3*. 

    ![Figure 9.3: After selecting the Liferay version, simply select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3.  Select the appropriate zip file. The zip files use naming convention
    `liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay EE/CE artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using a Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. We'll show you how to build the artifacts
from Liferay's source code next. 

### Building CE Maven Artifacts from Source [](id=liferay-portal-6-1-build-CE-maven-artifacts)

Downloading the Liferay Maven artifacts from Liferay's customer portal (EE) or
from Sourceforge (CE) is useful if you're interested in using the artifacts for
a particular release. However, if you'd like to use the very latest Liferay CE
Maven artifacts, you can build them from source. To build the latest Liferay CE
Maven artifacts from source, follow these steps:

1.  Navigate to your local Liferay Portal CE source project. If you don't
    already have a local Liferay Portal CE source project on your machine, you
    can fork the Liferay Portal CE Github repository, found at
    [http://github.com/liferay/liferay-portal](http://github.com/liferay/liferay-portal),
    and clone it your machine.

2.  Create an `app.server.[user name].properties` file in your local Liferay
    Portal CE source project root directory. Specify the following properties in
    it:

        app.server.parent.dir=[app server parent directory]

        app.server.[app server name].dir=[app server directory]

    Of course, add the path of your application server's parent directory and
    the path of your application server itself after the appropriate equals
    signs. Also, replace `[app server name]` with the name of your application
    server. Note that your `app.server.[app server name].dir` directory doesn't
    need to exist yet; you can create it by invoking an Ant target in the next
    step. For example, if you're running Apache Tomcat and your
    Liferay home directory is `/home/jbloggs/liferay/bundles/ce-6.1.x`, use
    the following properties:

        app.server.parent.dir=/home/jbloggs/liferay/bundles/ce-6.1.x

        app.server.tomcat.dir=/home/jbloggs/liferay/bundles/ce-6.1.x/[tomcat version]

3.  If an application server doesn't already exist at the directory specified by
    your `app.server.[app server name].dir` property, run `ant -f build-dist.xml
    unzip-[app server name]` to unzip a copy of your preferred application
    server to the specified directory.
   
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
    
    **Note**: The `releases.[user name].properties` is not required if you only
    plan to install the Liferay artifacts locally and not deploy them.

5.  Open a command prompt, navigate to your Liferay home directory, and build
    the Liferay artifacts by running

        ant clean start jar

6.  Build the Liferay Portal WAR file by running 

        ant -f build-dist.xml all zip-portal-war

7.  Deploy the Liferay artifacts to your Maven repository by running 

        ant -f build-maven.xml deploy-artifacts

    If you want the Liferay artifacts to be installed locally but don't have a
    remote Maven repository or don't want the artifacts to be remotely deployed,
    you can run the install target instead of the deploy
    target: `ant -f build-maven.xml install-artifacts`. Once the Ant target
    finishes, you should have a time-stamped directory containing the artifacts
    in your Local Liferay Portal CE source project's root directory (e.g.,
    `liferay-portal/20121105174417071`).

---

![note](../../images/tip-pen-paper.png) **Warning:** During the process of
 packaging up the `javadoc.jar` files for your Liferay artifacts, your machine
 may experience sluggish performance or an insufficient amount of Java heap space.
 There are two solutions to this problem:
 
- **Increase the memory available for the Javadoc packaging process:**
  Navigate to `[Liferay home]/build.xml` and search for the `javadoc` target.
  Find the `maxmemory` property and increase it as desired.
- **Skip the Javadoc packaging process:** Navigate to
  `[Liferay home]/build-maven.xml` and find the `prepare-maven` target. Within
  this target, comment out the call to the `jar-javadoc` target, like below:

		<!-- <antcall target="jar-javadoc" /> -->
		
---

Next, we'll show you how to install the required Liferay release artifacts to
your repositories. These steps are applicable for installing artifacts that you
downloaded and extracted from a zip file, and for installing artifacts that you
built from source. 

#### Installing Artifacts to a Repository [](id=installing-downloaded-artifacts-to-a-repository)

Let's install the Liferay release artifacts to your local Maven repository for
sharing with your team.

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

#### Deploying Artifacts to a Repository [](id=deploying-downloaded-artifacts-to-a-repository)

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1.  Make sure you've created a `liferay-releases` repository server to hold the
    Liferay Maven artifacts. If you haven't, see the *Managing Maven
    Repositories* section for instructions. 

2.  Ensure the repository to hold your Liferay artifacts is specified as a
    server in Maven's `settings.xml` file. If it isn't already, see the
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

3.  Using your command prompt, navigate to the `liferay-portal-maven-[version]`
    directory, or to your time-stamped Liferay Portal artifacts directory. This
    is the root directory extracted from the Liferay artifacts zip file. Or
    navigate to your time-stamped Liferay Portal artifacts directory, if you
    built the artifacts from source. 

4.  Create a `build.[user name].properties` file (e.g.,
    `build.jbloggs.properties`) in your `liferay-portal-maven-[version]`
    directory. In the new properties file, specify values for the properties
    `lp.maven.repository.id` and `lp.maven.repository.url`. These refer to your
    repository's ID and URL, respectively.

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

    To verify your artifacts are deployed, navigate to the *Repositories* page
    of your Nexus OSS server and select your repository.

    ![Figure 9.4: You can easily navigate to your Liferay release repository in Nexus.](../../images/maven-select-repository2.png)

    Notice a window appears below displaying the Liferay artifacts now deployed
    to your repository.

![Figure 9.5: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

If you're working with Liferay CE, there's an alternative method of obtaining
the necessary Liferay Maven artifacts: you can let Maven download them
automatically. Let's see how. 

### Installing CE Artifacts from the Central Repository [](id=installing-artifacts-from-the-central-repository)

Liferay offers an option for automatic download and installation of Liferay CE
Maven artifacts. They're publicly available on the *Central Repository*, located
at
[http://search.maven.org/#search|ga|1|liferay%20maven](http://search.maven.org/#search|ga|1|liferay%20maven),
and are updated with each Liferay release (e.g., 6.1.0, 6.1.10, 6.1.20, etc.).
The first time you use Maven to compile a Liferay plugin project, Maven
automatically downloads the required artifacts from the Central Repository into
your local repository if they're not found in your local repository or any of
your configured repository servers. You'll see it happen when you package your
Liferay CE plugins. 

Now that we have our Maven artifacts set up, let's configure Liferay IDE with
Maven.

## Using Liferay IDE with Maven [](id=use-liferay-ide-with-maven-liferay-portal-6-1-dev-guide-en)

Wouldn't it be nice if you could manage your Liferay Maven projects from Liferay
IDE? You can! Liferay IDE 2.0 introduces the Maven project configurator
(`m2e-liferay`), or the added support of configuring Maven projects as full
Liferay IDE projects. Let's explore what the Maven project configurator does,
how to install it, and how to install its dependencies. 

### Installing Maven Plugins for Liferay IDE [](id=install-maven-plugins-for-liferay-ide-liferay-portal-6-1-dev-guide-en)

In order to properly support Maven projects in the IDE, you first need a
mechanism to recognize Maven projects as Liferay IDE projects. IDE projects are
recognized in Eclipse as faceted web projects that include the appropriate
Liferay plugin facet. Therefore, all IDE projects are also Eclipse web projects
(faceted projects with web facet installed). In order for the IDE to recognize
the Maven project and for it to be able to leverage Java EE tooling features
(e.g., the *Servers* view) with the project, the project must be a flexible web
project. Liferay IDE requires that the following Eclipse plugins be installed
in order for Maven projects to meet these requirements: 

- `m2e-core` (Maven integration for Eclipse)
- `m2e-wtp` (Maven integration for WTP)

When you install the `m2e-liferay` plugin, these dependencies are installed by
default. We'll flesh out the installation process soon, but first, let's get a
deeper understanding of how these plugins work to give us our IDE/Maven
compatibility. 

The `m2e-core` plugin is the standard Maven tooling support for Eclipse. It
provides dependency resolution classpath management and an abstract project
configuration framework for adapters. Also, in order for a Liferay Maven project
to be recognized as a flexible web project, the Maven project must be mapped to
a flexible web project counterpart. The `m2e-wtp` plugin. provides project
configuration mapping between the Maven models described in the Maven project's
POMs and the corresponding flexible web project supported in Eclipse. With these
integration features in place, the only remaining requirement is making sure
that the `m2e-core` plugin can recognize the extra lifecycle metadata mappings
necessary for supporting Liferay's custom goals. Let's break down the lifecycle
mappings just a bit to get a better understanding of what this means. 

Both Maven and Eclipse have their own standard build project lifecycles that are
independent from each other. Therefore, for both to work together and run
seamlessly within Liferay IDE, you need a lifecycle mapping to link both
lifecycles into one combined lifecycle. Normally, this would have to be done
manually by the user. However, with the `m2e-liferay` plugin, the lifecycle
metadata mapping and Eclipse build lifecycles are automatically combined
providing a seamless user experience. The lifecycle mappings for your project
can be viewed by right-clicking your project and selecting *Properties* &rarr;
*Maven* &rarr; *Lifecycle Mapping*. 

![Figure 9.6: View your project's lifecycle mappings to verify successful plugin execution.](../../images/maven-lifecycle-mapping.png)

When first installing Liferay IDE, the installation startup screen lets you
select whether you'd like to install the Maven plugins automatically. Did you
miss this during setup? No problem! To install the required Maven plugins,
navigate to *Help* &rarr; *Install New Software*. In the *Work with* field,
insert the following: `Liferay IDE repository -
http://releases.liferay.com/tools/ide/latest/milestone/`. 

If the `m2e-liferay` plugin does not appear, this means you already have it
installed. To verify, uncheck the *Hide items that are already installed*
checkbox and look for `m2e-liferay` in the list of installed plugins. Also, if
you'd like to view everything that is bundled with the `m2e-liferay` plugin,
uncheck the *Group items by category* checkbox. 

![Figure 9.7: You can install the `m2e-liferay` plugin by searching for software on Liferay IDE's repository.](../../images/m2e-liferay-installation.png)

Awesome! The required Maven plugins are installed and your IDE instance is ready
to be mavenized! Next, let's learn how to configure an existing Maven project.

### Configuring your Liferay Maven Project [](id=configure-liferay-maven-project-liferay-portal-6-1-dev-guide-en)

Now your Liferay IDE instance is Maven-ready and you have an existing Maven
project. Let's investigate what is going on under the hood and configure your
project. Note, if you'd like to learn how to create a new Maven project in the
IDE, visit the *Creating Liferay Plugins with Maven* section. Furthermore, you
can import an existing Maven project by navigating to *File* &rarr; *Import*
&rarr; *Maven* and selecting the location of your Maven project source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** Due to the lifecycle mapping
of Eclipse and Maven, it is unsafe to manually insert or overwrite the
`.classpath` and `.project` files and `.settings` folder. IDE automatically
generates these files when a project is imported and updates them appropriately.

---

The `m2e-core` plugin delegates your Liferay Maven plugin's project
configuration to the `m2e-liferay` project configurator. The `m2e-wtp` project
configurator then converts your Liferay WAR package into an Eclipse flexible web
project. Next, the `m2e-liferay` configurator looks for the Liferay Maven plugin
to be registered on the POM effective model for WAR type packages. If no Liferay
Maven plugin is configured on the effective POM for the project, project
configuration ceases. If the plugin is configured, the project configurator
validates your project's configuration, checking it's POM, parent POM, and the
project's properties. The configurator detects invalid properties and reports
them as errors in the IDE's POM editor. There are a list of key properties that
your project must specify in order for it to become a valid Liferay IDE project.
The next section titled *Using a Parent Plugin Project* identifies these
properties and explains how they are used. 

There are various ways to satisfy these properties--the Maven profile in the
Global `settings.xml` file (recommended), in the User `settings.xml` file, in
the parent `pom.xml`, or in the project `pom.xml` directly. You can think of
these choices as a hierarchy for how your Maven plugins receive their
properties. The project `pom.xml` overrides the parent `pom.xml`, the parent
`pom.xml` overrides the User `settings.xml` file, and the User `settings.xml`
file overrides the Global `settings.xml` file. 

**Global `settings.xml`:** provides configuration for all plugins belonging to
all users on a machine. This file resides in the
`${MAVEN_HOME}/conf/settings.xml` directory.

**User `settings.xml`:** provides configuration for all plugins belonging to a
single user on a machine. This file resides in the
`${USER_HOME}/.m2/settings.xml` directory.

**Parent `pom.xml`:** provides configuration for all modules in the parent
project.

**Project `pom.xml`:** provides configuration for the single plugin project.

Note that if a profile is active from your `settings.xml`, its values will
override your properties in a POM. If you'd like to specify the properties in a
POM, see the next section *Using a Parent Plugin Project* for more details. 

Here's an example of what a Maven profile looks like inside the `settings.xml`
file.

    <profiles>
        <profile>
            <id>sample</id>
                <properties>
                    <plugin.type>portlet</plugin.type>
                    <liferay.version>6.2.0</liferay.version>
                    <liferay.maven.plugin.version>6.2.0</liferay.maven.plugin.version>
                    <liferay.auto.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\deploy</liferay.auto.deploy.dir>
                    <liferay.app.server.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps</liferay.app.server.deploy.dir>
                    <liferay.app.server.lib.global.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\lib\ext</liferay.app.server.lib.global.dir>
                    <liferay.app.server.portal.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps\ROOT</liferay.app.server.portal.dir>
                </properties>
        </profile>
    </profiles>

Once you have a Maven profile configured in the `${USER_HOME}/.m2/settings.xml`
file, you can activate the profile by right-clicking on your project &rarr;
*Properties* &rarr; *Maven* and entering the profile IDs that supply the
necessary settings into the *Active Maven Profiles* text field. For example, to
reference the profile and properties we listed above, you'd enter *sample* for
the Active Maven Profile. Once you've specified all the values, the configurator
(`m2e-liferay`) validates the properties. If there are errors in the `pom.xml`
file, the configurator marks them in Liferay IDE's POM editor. If you fix a
project error, update the project to persist the fix by right-clicking the
project &rarr; *Maven* &rarr; *Update Project*. 

After your POM configuration meets the requirements, the configurator installs
the Liferay plugin facet and your Maven project is officially a Liferay IDE
project! 

Once you have your Maven project configured, you may want to execute a specific
Maven goal such as `liferay:build-lang` or `liferay:build-db` that is associated
with your build phase. To access your project's Maven goals and execute them,
right-click your project &rarr; *Liferay* &rarr; *Maven* and select the goal to
execute. To learn more about Maven's build lifecycle and plugin goals, visit
Apache's [Build Lifecycle
Basics](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Build_Lifecycle_Basics)
guide.

When working with your `pom.xml` file in the IDE, you'll notice several
different viewing modes to work with:

**pom.xml:** provides an editable POM as it appears on the file system.

**Effective POM:** provides a read-only version of your project POM merged with
its parent POM(s), `settings.xml`, and the settings in Eclipse for Maven.

**Overview:** provides a graphical interface where you can add to and edit the
`pom.xml` file.

**Dependencies:** provides a graphical interface for adding and editing
dependencies in your project, as well as modifying the `dependencyManagement`
section of the `pom.xml` file.

**Dependency Hierarchy:** provides hierarchical view of project dependencies and
interactive list of resolved dependencies.

![Figure 9.8: Liferay IDE provides five interactive modes to help you edit and organize your POM.](../../images/pom-editor-features.png)

By taking advantage of these interactive modes, modifying and organizing your
POM and its dependencies has never been easier!

Next, we'll consider the benefits of using a Maven parent project with your
plugin projects. 

## Using a Parent Plugin Project [](id=using-a-parent-plugin-project)

Maven supports project inheritance. You can create a *parent* project that
contains properties child projects have in common, and *child* projects
inherit those properties from the parent project. This saves time, since you
don't need to specify those properties in each project. If you develop more
than one project, it makes sense to leverage project inheritance so that all
projects can share properties they have in common. 

Our example demonstrates project inheritance; we'll build a project with a
parent/child relationship. Even if you're not going to leverage Maven's project
inheritance capabilities when you build your Liferay plugins with Maven, the
process is the same for creating any Liferay plugin with Maven's Liferay
artifacts. For more information on project inheritance, see Maven's
documentation at
[http://maven.apache.org/pom.html#Inheritance](http://maven.apache.org/pom.html#Inheritance). 

Let's create our parent project, and then specify the general settings you'll
need to build your plugins for Liferay. The parent project is similar to the
project root of the Liferay Plugins SDK. Its `pom.xml` file can specify
information to be used by any plugin projects that refer to it. You can always
specify information in each plugin's POM, but it's more convenient to use the
parent project's POM for sharing common information. 

Let's create a parent project named `sample-parent-project`: 

1.  Create a new directory for your parent project. For this example, we'll name
    the directory `sample-parent-project`. You can place the directory anywhere
    on your file system. 

2.  Inside the `sample-parent-project` directory, create a `pom.xml` file and
    insert the following lines: 

        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>

            <groupId>com.liferay.sample</groupId>
            <artifactId>sample-parent-project</artifactId>
            <version>1.0-SNAPSHOT</version>
            <packaging>pom</packaging>

            <name>sample-parent-project</name>
            <url>http://www.liferay.com</url>

            <properties>
                <liferay.auto.deploy.dir>${liferay.auto.deploy.dir}</liferay.auto.deploy.dir>
                <liferay.version>${liferay.version}</liferay.version>
            </properties>
    
            <dependencies>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-client</artifactId>
                    <version>6.1.30</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-impl</artifactId>
                    <version>6.1.30</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-service</artifactId>
                    <version>6.1.30</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-web</artifactId>
                    <version>6.1.30</version>
                    <type>war</type>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-bridges</artifactId>
                    <version>6.1.30</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-java</artifactId>
                    <version>6.1.30</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-taglib</artifactId>
                    <version>6.1.30</version>
                </dependency>
            </dependencies>

        </project>

    If you use this example POM, replace the `<version>` values with the version
    of Liferay applicable to the plugins you're developing. 
    
    You can also specify these key properties in your Global or User
    `settings.xml` file. To learn more about this method, visit the *Configuring
    Your Liferay Maven Project* section.
    
3.  Modify the values of the *properties* to match your Liferay environment.

    Fill in the `<liferay.auto.deploy.dir>...</liferay.auto.deploy.dir>` tags
    with the path of your Liferay bundle's `deploy` directory. This is the
    auto-deploy directory you will eventually copy your plugin to deploy on
    Liferay.

    Fill in the `<liferay.version>...</liferay.version>` tags with the version
    of Liferay you are using.

    Your POM's *properties* should look similar to the following:
    
        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.30-ee-ga3\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.30</liferay.version>
        </properties>

By specifying your Liferay instance's deploy directory in the POM, you're
telling Maven exactly where to deploy your plugin artifacts. 

Your parent project now specifies common dependencies on required Liferay Maven
artifacts. All your parent project's modules (i.e., projects that refer to this
parent) can leverage these dependencies. 

---

![note](../../images/tip-pen-paper.png) **Note:** You could just as easily
include such dependencies in the POM of each of your plugin projects, but
specifying them in a parent project makes them accessible to child projects
through inheritance. 

---
 
Now you're able to specify dependencies on required Liferay artifacts; let's
create a Liferay plugin project using the archetypes provided by Liferay. 

## Creating Liferay Plugins with Maven [](id=creating-liferay-plugins-with-maven)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that its executable is in your path environment variable. 

---

We'll demonstrate two ways of creating Liferay plugins with Maven: using Liferay
IDE and using the command line. First, let's learn how to use Maven archetypes
to generate a Liferay plugin project using Liferay IDE:

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name. For our example, we'll use
    *sample-portlet* and *Sample* for the project name and display name,
    respectively. Notice that upon entering *sample-portlet* as the project
    name, the wizard conveniently inserts *Sample* in grayed-out text as the
    portlet's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Portlet* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name. 

3.  Select *Maven (liferay-maven-plugin)* for the build type. Notice that some
    of the options for your plugin project changed, including the *Location* 
    field, which is set to the user's workspace by default. Choose the parent 
    directory in which you want to create the plugin project. It is a best 
    practice to create a parent project for your Maven plugins, so they can all
    share common project information. See section *Using a Parent Plugin
    Project* for details. 

4.  Specify the *Artifact version* and *Group id*. For our example, we'll use
    `1.0-SNAPSHOT` and `com.liferay.sample` for the artifact version and group
    id, respectively.

5.  Specify the active profile that you'd like your Liferay plugin project to
    use. If you don't remember your active profile or haven't created one, click
    the *Select Active Profiles ...* icon immediately to the right of the text
    field. If you have any active profiles, they will be listed in the menu on
    the left. To select an existing profile, highlight its profile ID and select
    the illuminated *right arrow* button to transfer it to the menu on the
    right. Otherwise, if you don't have any existing profile, click the green
    *addition* button to create a profile and give it an ID. 

    If you're specifying a new profile, the wizard will still create your
    plugin, but it will need further attention before it is deployable. You'll
    need to specify the necessary properties within the new profile; we'll
    demonstrate specifying these properties in the *Configuring your Liferay
    Maven Project* section of this chapter. 

    You also have the option to create a profile based on a Liferay runtime. To
    do this, select the *Create New Maven Profile Based on Liferay Runtime*
    button to the far right of the *Active profiles* text field. Specify the
    *Liferay runtime*, *New profile id*, and *Liferay version*. For the new
    profile location you can choose to specify your profile in the
    `settings.xml` (recommended) or your project's `pom.xml`. When creating your
    Maven profile based on a Liferay runtime, the IDE automatically populates
    the new profile with the required properties, and no additional profile 
    configuration is needed for the plugin. 

6.  Select the *Portlet* plugin type and then click *Finish*.

	![Figure 9.9: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! Next, let's run through steps for creating your Liferay Maven
plugins using the command line. 

1.  Open the command prompt and navigate to the parent directory in which you
    want to create the plugin project. Archetype will create a sub-directory for
    the plugin project you create.

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you haven't already
    created a parent project, you may want to consider creating one to share
    common project information. See section *Using a Parent Plugin Project* for
    details.

    ---

2.  Execute the command

        mvn archetype:generate

    Archetype starts and lists the archetypes available to you. You're prompted
    to *choose* an archetype or *filter* archetypes by group / artifact ID. The
    output looks similar to the following text: 
    
        ...
        39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
        (Provides an archetype to create Liferay hooks.)
        40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype
        (Provides an archetype to create Liferay ICEfaces portlets.)
        43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):

3.  To find the right Liferay archetype for your project, you can either scroll
    up to find it or apply filters to narrow the set of results. Filtering on
    *liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*,
    etc.) can help you focus on more applicable Liferay archetypes. 

    Entering `liferay:portlet` as a filter gives a listing of Liferay portlet
    archetypes: 

        Choose a number or apply filter (format: [groupId:]artifactId, case
        sensitive contains): : liferay:portlet Choose archetype:
        1: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        2: remote ->
        com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype (Pr
        ovides an archetype to create Liferay ICEfaces portlets.)
        3: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provide s an archetype to create Liferay JSF portlets.)
        4: remote ->
        com.liferay.maven.archetypes:liferay-portlet-liferay-faces-alloy-ar
        chetype (Provides an archetype to create Liferay Faces Alloy portlets.)
        5: remote ->
        com.liferay.maven.archetypes:liferay-portlet-primefaces-archetype (
        Provides an archetype to create Liferay PrimeFaces portlets.)
        6: remote ->
        com.liferay.maven.archetypes:liferay-portlet-richfaces-archetype (P
        rovides an archetype to create Liferay RichFaces portlets.) Choose a
        number or apply filter (format: [groupId:]artifactId, case sensitive co
        ntains): :

4.  Choose an archetype by entering its number.

5.  You're prompted to choose the archetype version. Enter the number
    corresponding to the Liferay version for the archetype. However, you're not
    required to select the archetype version that corresponds with your Liferay
    instance; older archetype versions are compatible with updated Liferay
    bundles. 

    The snapshot below illustrates choosing the archetype version: 

    ![Figure 9.10: You're prompted by Maven Archetype to enter the archetype version.](../../images/maven-archetype-version.png)

6.  Enter values for the *groupId*, *artifactId*, *version*, and *package*
    coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 9.11: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).
    
7.  Enter the letter `Y` to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/<archetype>`). If you configured a
mirror pointing to your public repository on Nexus, the plugin is installed
there. 

---
    
![Figure 9.12: Your archetype and its dependencies are now available in your local repository.](../../images/maven-public-plugin.png)

Following these steps using Liferay IDE or the command line, you can use
Archetype to generate all your Liferay projects!

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 

## Deploying Liferay Plugins with Maven [](id=deploying-liferay-plugins-with-maven)

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1.  Make sure your parent POM's Liferay-specific properties specify the correct
    Liferay version and your Liferay Portal's deploy directory path. 

    Here's an example of these properties using Liferay 6.1.30 and a deploy
    directory path of `E:\liferay-portal-6.1.30-ee-ga3\deploy`: 

        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.30-ee-ga3\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.30</liferay.version>
        </properties>

    If you haven't already created a parent plugin project, see *Using a Parent
    Plugin Project* for details. 

2.  In your command prompt, navigate to your Liferay plugin project's directory. 

3.  Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\ce-plugins-maven\sample-parent-project\sample-portlet\target\sample-portlet-1.0-SNAPSHOT.war
        ...
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

4.  Deploy the plugin into your Liferay bundle by entering

        mvn liferay:deploy

    The command output should look similar to the following output: 

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

    Your Liferay console output shows your plugin deploying. It looks
    like this: 

        INFO: Deploying web application directory [liferay version]\[tomcat version]\webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from queue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for sample-portlet

    ---
 
    ![note](../../images/tip-pen-paper.png) **Note:** If you get the following
    error after executing `mvn liferay:deploy`, make sure you're executing the
    command from your plugin's directory (e.g., `sample-portlet`). 
 
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    ---

5.  If you're deploying the plugin to a release or snapshot repository,
    specify the repository by adding a distribution management section to your
    plugin's `pom.xml`. 

    Here's an example distribution management section for a snapshot repository: 

        <distributionManagement>
            <repository>
                <id>liferay-releases</id>
                <url>http://localhost:8081/nexus/content/repositories/liferay-releases</url>
            </repository>
            <snapshotRepository>
                <id>liferay-snapshots</id>
                <name>Liferay Snapshots Repository</name>
                <url>http://localhost:8081/nexus/content/repositories/liferay-snapshots</url>
            </snapshotRepository>
        </distributionManagement>

    The proper contents for your `<distributionManagement>` element can be found
    in the *Summary* tab for each of your repositories. 

    ![Figure 9.13: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    Since you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6.  Deploy your plugin into your specified Nexus repository:

		mvn deploy

---

![note](../../images/tip-pen-paper.png) **Note:** There are three build phases
you'll use when developing plugins with Maven: 

- In Maven's *compile* phase, explicit dependencies are downloaded to your
  local repository (i.e., `.m2/repository/com/liferay/portal`).
- In Maven's *package* phase, the plugin's inferred dependencies are downloaded
  to your local repository (i.e., `.m2/repository`). 
- In Maven's *install* phase, your plugin is installed to your local
  repository. 

---

Now that you've deployed a plugin using Maven, let's consider the types of
Liferay plugins you can develop with Liferay Maven archetypes. 

## Liferay Plugin Types to Develop with Maven [](id=liferay-plugin-types-to-develop-with-maven)

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


### Creating a Portlet Plugin [](id=developing-liferay-portlet-plugins-with-maven)

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to 
find the Liferay portlet archetypes more easily. 

---

#### Anatomy [](id=liferay-6-1-maven-portlet-plugin-anatomy)

A portlet project created from the
`com.liferay.maven.archetypes:liferay-portlet-archetype` has the following
directory structure: 

- portlet-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - css/
                    - main.css
                - js/
                    - main.js
                - WEB-INF/
                    - liferay-display.xml
                    - liferay-plugin-package.properties
                    - liferay-portlet.xml
                    - portlet.xml
                    - web.xml
                - icon.png
                - view.jsp
    - pom.xml

The `portlet-plugin/src/main/java/` directory is created automatically when you
create a portlet project with Maven. It holds the portlet's Java source code
(e.g., `com.liferay.sample.SamplePortlet.java`), and
`portlet-plugin/src/main/webapp` holds its web source code. If you've created
any portlet plugins using the Plugins SDK, you might have noted it uses a
different directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the full directory structure of a portlet developed by Ant, visit our
[Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-portl-4)
section in this guide. 

#### Deployment [](id=liferay-6-1-deploy-portlet-with-maven)

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

#### More Information [](id=liferay-6-1-maven-portlet-more-info)

For detailed information on creating portlet plugins, see Chapter 3, 
[Portlet Development](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/portlet-developme-3). 

Next, let's run through a brief example for developing a theme plugin using
Maven.

### Developing Liferay Theme Plugins with Maven [](id=developing-liferay-theme-plugins-with-maven)

So you're sitting in your armchair next to the fire, just as we described in our
chapter introduction; shadows dance on the tapestry-covered wall, and Lenore II
(your cat) is purring atop the mantle. Yes, you're passing this cold winter's
night in grand style (in front of your computer, of course). Now imagine
yourself sitting on a cold hard wooden chair inside an off-white cubicle with
empty walls (you're still in front of your computer, of course). These two
descriptions paint two very different pictures, but both describe what you're
doing (sitting and computing). Changing the "scenery" of your portal sets the
mood for your users. We'll show you how to develop your own theme plugin (i.e.,
your "scenery") using Maven so your portal has a lasting impression on anyone
who visits. 

#### Creating a Theme Plugin [](id=liferay-6-1-create-theme-with-maven)

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section, making sure to select *Theme* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your theme project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to more easily
find the Liferay portlet archetypes. 

---

#### Anatomy [](id=liferay-6-1-maven-theme-plugin-anatomy)

A theme project created from the
`com.liferay.maven.archetypes:liferay-theme-archetype` has the following
directory structure: 

- sample-theme/
    - pom.xml
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-plugin-package.properties
                    - web.xml
            - css/
            - images/
            - js/
            - templates/

Several of the directories listed in the structure above are not created
automatically; you'll create them as needed, depending on the customizations
you're making. Here's a list of these directories, with a brief description of
each: 

- `src/main/css/`: Holds your CSS customizations.
- `src/main/images/`: Holds custom images for your theme.
- `src/main/js/`: Holds your JavaScript customizations.
- `src/main/templates/`: Holds your template customizations. 

The `src/main/webapp/` folder contains your theme's customizations. If you've
ever created a theme plugin using the Plugins SDK, this folder is used the same
way as the `docroot/_diffs/` folder. For example, `custom.css` should go in
`src/main/webapp/css/custom.css`. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project     | Plugins SDK project |
-------------- | ----------------- | ------------------- |
customizations | `src/main/webapp` | `docroot/_diffs`    |

To view the directory structure of a theme developed by Ant, visit the [Anatomy
of a
Theme](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-the-4)
section in this guide. 

#### Theme POM [](id=liferay-6-1-theme-pom-maven)

The theme plugin project POM has two additional properties: 

-   `liferay.theme.parent`: Sets the parent theme. You can define almost any WAR
    artifact as the parent using the syntax `groupId:artifactId:version`, or use
    the core themes by specifying `_unstyled`, `_styled`, `classic`, or
    `control_panel`. 
-   `liferay.theme.type`: Sets the template theme language. 

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

#### Deployment [](id=liferay-6-1-deploy-theme-with-maven)

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
Plugins with Maven* section. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you execute the *package*
goal, a WAR file is created; it's just like the Maven WAR type project.
Simultaneously, the parent theme is downloaded and copied, and your theme's
customizations are overlaid last. A thumbnail image of the theme is created and
placed in the *target* directory. Its path is
`target/<theme>/images/screenshot.png` in your theme project. 

---

#### More Information  [](id=lp-6-1-dgen08-more-information--0)

For more information on Liferay themes and its settings, see Chapter 5,
[Creating Liferay
Themes](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-liferay-them-7).

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

### Developing Liferay Hook Plugins with Maven [](id=developing-liferay-hook-plugins-with-maven)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

#### Creating a Hook Plugin [](id=liferay-6-1-create-hook-with-maven)

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section, making sure to select *Hook* as
the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:portlet`, to more easily find the
Liferay portlet archetypes. 

---

#### Anatomy [](id=liferay-6-1-maven-hook-plugin-anatomy)

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/ \* Optionally add to hold Java source
            - webapp/
                - WEB-INF/
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`), and `hook-plugin/src/main/webapp`
holds its web source code. If you're familiar with creating hook plugins using
the Plugins SDK, you probably noticed that Maven uses a different plugin
directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, visit the *Anatomy
of a Hook* section of the [Creating a
Hook](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-a-ho-4)
section in this guide. 

#### Deployment [](id=liferay-6-1-deploy-hook-with-maven)

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

#### More Information [](id=liferay-6-1-maven-hook-more-info)

For detailed information on creating hooks, see Chapter 7,
[Hooks](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/hoo-4). 

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 

### Developing Liferay Layout Template Plugins with Maven [](id=developing-liferay-layout-template-plugins-with-maven)

You can create layout templates to customize the display of portlets on your
page and to embed commonly used portlets. In our introduction to themes, we
described a nice scene where you're relaxing in a luxurious chair, computer in
your lap, Lenore II (your cat) purring on the mantle above a dancing fire.
Sounds nice, doesn't it? It would be, but the chair's too small, so your knees
are up in the air when your feet are flat on the ground, and your laptop is
balanced precariously on top of them. The fire is also surprisingly large for
that fireplace. In fact, its flames are already licking at the bottom of the
mantle--which is made of wood! Remember Lenore II, softly purring on the mantle?
She's going to cook just like the original Lenore if we don't do something!
But it's so hard to get out of this tiny chair. Someone save Lenore II! 

"Tell this soul with sorrow laden if, within the distant Aidenn,
It shall clasp a sainted kitten whom the angels named Lenore II--"

In memory of the late, now cripsy Lenore II, let's create a layout template
plugin with Maven. 

#### Creating a Layout Template Plugin [](id=liferay-6-1-create-layout-template-with-maven)

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section, making sure to select *Layout Template* as the
plugin type.

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your layout template project, you can filter on group ID
`liferay`, or even group ID / artifact ID combination `liferay:layout`, to find
the Liferay layout template archetypes.

---

#### Anatomy [](id=liferay-6-1-maven-layout-template-plugin-anatomy)

A layout template project created from the
`com.liferay.maven.archetypes:liferay-layouttpl-archetype` has the following
directory structure:

- layouttpl-plugin/
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-layout-templates.xml
                    - liferay-plugin-package.properties
                    - web.xml
                - sample-layout.png
                - sample-layout.tpl
                - sample-layout.wap.tpl
    - pom.xml

There's a directory structure difference between plugin projects created using
Liferay Maven archetypes and those created using the Liferay Plugins SDK. The
following table illustrates this difference: 

Location   | Maven project     | Plugins SDK project |
---------- | ----------------- | ------------------- |
Web source | `src/main/webapp` | `docroot` |

To view the directory structure of a layout template developed by Ant, visit the
[Anatomy of a Layout Template
Project](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-anatomy-of-a-layout-template-0)
section in this guide. 

#### Deployment [](id=liferay-6-1-deploy-layout-template-with-maven)

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

#### More Information [](id=liferay-6-1-maven-layout-template-more-info)

For detailed information on creating layout templates, see Chapter 6, [Creating
Liferay Layout
Templates](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-creating-liferay-layout-templates-0). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 

### Developing More Liferay Plugins with Maven [](id=developing-more-liferay-plugins-with-maven)

Did you think we covered all the available archetypes for developing Liferay
plugins? The Liferay team has been busy expanding our archetype list, and we're
proud to show you some additional plugins that you can create using Maven
archetypes. 

Check out these exciting archetypes that are now available: 

- Liferay ServiceBuilder portlets 
- Liferay webs 
- Liferay Ext 
- JSF Portlet Archetype 
- ICEFaces Portlet Archetype 
- PrimeFaces Portlet Archetype 
- Liferay Faces Alloy Portlet Archetype 
- Liferay Rich Faces Portlet Archetype 

In addition, there are two new Maven *goals* now available: 

- DBBuilder - The `build-db` goal lets you execute the DBBuilder to generate SQL
  files. 
- SassToCSSBuilder - The `build-css` goal precompiles SASS in your css; this
  goal has been added to theme archetype. 

You now have plenty of archetypes at your disposal! 

## Summary [](id=summ-42)

"But the chair whose violet lining with the lamp-light gloating o'er,
Lenore II shall press, ah, nevermore!"

Lenore II didn't make it through the chapter, but you did. You can develop all
your Liferay plugins using Maven; there's a standard process for generating the
archetypes and selecting your plugin options for each plugin type. You can then
customize the archetype to your liking. Using Maven to develop plugins offers an
easy and effective way to customize your Liferay Portal. 

You accomplished a lot in this chapter. You configured Maven locally, downloaded
and installed the required Liferay Maven artifacts, and learned to create
Liferay plugins with Maven. You're ready to create all kinds of Liferay plugins
based on Liferay's plugin archetypes. 

We've explored Maven in this chapter, and the Plugins SDK in earlier chapters.
You've seen two powerful tools you can use to develop your Liferay plugins. In
addition to these, there's Liferay IDE and Liferay Developer Studio! Let's look
at Liferay IDE in the next chapter. 

---

![note](../../images/tip-pen-paper.png) **Note:** Are you wondering if we're
 going to make more terrible jokes that steal from classic poetry? Quoth the
 Maven, "Probably." 

---

<!-- I thought Lenore II did make it through the chapter. Did you forget to kill
her off? --> 

<!-- I tried to make this more explicit in the intro to layout templates.
Hopefully not too explicit. -->
