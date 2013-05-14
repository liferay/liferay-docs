# Developing Plugins Using Maven

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

-Offers a simple build process. 

-Features a project object model. 

-Has a defined project life cycle. 

-Provides a dependency management system. 

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

## Installing Maven 

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
path, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

## Using Maven Repositories 

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

### Managing Maven Repositories 

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

1. Open your web browser; navigate to your Nexus repository server (e.g.,
[http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. 

2. Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 8.1: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

    ![note](../../images/tip-pen-paper.png) **Note:** To learn more about each
    type of Nexus repository, read Sonatype's *Managing Repositories* at
    [http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3. Enter repository properties appropriate to the access you'll provide its
artifacts. We're installing release version artifacts into this repository, so
specify *Release* as the repository policy. Below are examples of repository
property values: 

    - *Repository ID:* `liferay-releases`

    - *Repository Name:* `Liferay Release Repository`

    - *Provider:* `Maven2`

    - *Repository Policy:* `Release`

4. Click *Save*.

You just created a Maven repository accessible from your Nexus OSS repository
server! Congratulations! 

Let's create a Maven repository to hold snapshots of each Liferay plugin we
create. Creating a *snapshot* repository is almost identical to creating a
*release* repository. The only difference is that we'll specify *Snapshot* as
its repository policy:

<!-- What is a snapshot? Sounds like Maven terminology that the reader might not
know. -->

1. Go to your Nexus repository server in your web browser.

2. Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

3. Specify repository properties like the following:

    - *Repository ID:* `liferay-snapshots`

    - *Repository Name:* `Liferay Snapshot Repository`

    - *Provider:* `Maven2`

    - *Repository Policy:* `Snapshot`

4. Click *Save*.

Voila! You not only have a repository for your Liferay releases (i.e.,
`liferay-releases`), you also have a repository for your Liferay plugin
snapshots (i.e., `liferay-snapshots`). 

Let's configure your new repository servers in your Maven environment so you can
install artifacts to the them. 

### Configuring Local Maven Settings 

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

1. Navigate to your `${USER_HOME}/.m2/` directory.

2. Open your `settings.xml` file. If it doesn't yet exist, create it.  

3. Provide settings for your repository servers. Here are contents from a
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

##  Installing Required Liferay Artifacts 

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

### Installing EE/CE Artifacts from a Zip File 

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file. Let's download the
Liferay EE artifacts first.

You can download the Liferay EE artifacts package from Liferay's Customer
Portal. Just follow these steps: 

1. Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2. Go to *Places* &rarr; *Customer Portal*.

3. Select *Liferay Portal* from the *Downloads* panel.

4. Inside *Filter by:*, select the appropriate Liferay version in the first
field and select the *Development* value in the second field.

    ![Figure 8.2: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5. Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

Get the artifacts for Liferay CE from SourceForge by following these steps:

1. Open your browser to *Liferay Portal* on SourceForge &rarr;
[http://sourceforge.net/projects/lportal/files/Liferay%20Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2. Select the Liferay version for which you need Maven artifacts. For example,
if you need Maven artifacts for Liferay Portal 6.1.1 CE GA2, select version
*6.1.1 GA2*. 

    ![Figure 8.3: After selecting the Liferay version, simply select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3. Select the appropriate zip file. The zip files use naming convention
`liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay EE/CE artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using a Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. We'll show you how to build the artifacts
from Liferay's source code next. 

### Building CE Maven Artifacts from Source

Downloading the Liferay Maven artifacts from Liferay's customer portal (EE) or
from Sourceforge (CE) is useful if you're interested in using the artifacts for
a particular release. However, if you'd like to use the very latest Liferay CE
Maven artifacts, you can build them from source. To build the latest Liferay CE
Maven artifacts from source, follow these steps:

1. Navigate to your local Liferay Portal CE source project. If you don't already
   have a local Liferay Portal CE source project on your machine, you can fork
   the Liferay Portal CE Github repository, found at
   [http://github.com/liferay/liferay-portal](http://github.com/liferay/liferay-portal),
   and clone it your machine.

2. Create a `app.server.[user name].properties` file in your local Liferay
   Portal CE source project root directory. Specify the following properties in
   it:

        app.server.parent.dir=[app server parent directory]

        app.server.[app server name].dir=[app server directory]

    Of course, add the path of your application server's parent directory and
    the path of your application server itself after the appropriate equals
    signs. Also, replace `[app server name]` with the name of your application
    server. Note that your `app.server.[app server name].dir` directory doesn't
    need to exist yet; you can create it by invoking an Ant target in the next
    step. For example, if you're running Apache Tomcat 7.0.27 and your
    Liferay home directory is `/home/jbloggs/liferay/bundles/ce-6.1.x`, use
    the following properties:

        app.server.parent.dir=/home/jbloggs/liferay/bundles/ce-6.1.x

        app.server.tomcat.dir=/home/jbloggs/liferay/bundles/ce-6.1.x/tomcat-7.0.27

3. If an application server doesn't already exist at the directory specified by
   your `app.server.[app server name].dir` property, run `ant -f build-dist.xml
   unzip-[app server name]` to unzip a copy of your preferred application
   server to the specified directory.
   
       For example, to unzip Apache Tomcat to the directory specified by your
       `app.server.tomcat.dir` property, run:
   
           ant -f build-dist.xml unzip-tomcat

4. Create a `releases.[user name].properties` in your local Liferay Portal CE
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

5. Open a command prompt, navigate to your Liferay home directory, and build the
   Liferay artifacts by running

    ant clean start jar

6. Build the Liferay Portal WAR file by running 

     ant -f build-dist.xml all zip-portal-war

7. Deploy the Liferay artifacts to your Maven repository by running 

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
 may experience sluggish performance or an insuffient amount of Java heap space.
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

#### Installing Artifacts to a Repository

Let's install the Liferay release artifacts to your local Maven repository for
sharing with your team.

1. If you downloaded a Liferay artifacts zip file, navigate to the
`liferay-portal-maven-[version]`directory. This is the root directory extracted
from the Liferay artifacts zip file. If you built the artifacts from source,
navigate to the time-stamped directory containing the artifacts in your Local
Liferay Portal CE source project's root directory, (e.g.,
`liferay-portal/20121105174417071`). 

2. To install the artifacts to your local repository, execute

        ant install

   Your console shows output from the artifacts being installed from the
   Liferay Maven package into your local repository, typically located in your
   `${USER_HOME}/.m2/repository` directory.

Your local repository now contains the Liferay artifacts required to build
Liferay plugins. Wasn't that easy?

If you want to share your Liferay artifacts with teammates, you'll have to
deploy them to a release repository server. 

#### Deploying Artifacts to a Repository 

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1. Make sure you've created a `liferay-releases` repository server to hold the
Liferay Maven artifacts. If you haven't, see the *Managing Maven Repositories*
section for instructions. 

2. Ensure the repository to hold your Liferay artifacts is specified as a server
in Maven's `settings.xml` file. If it isn't already, see the *Configuring Local
Maven Settings* section for instructions on adding an entry for the server. 

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

3. Using your command prompt, navigate to the `liferay-portal-maven-[version]`
directory, or to your time-stamped Liferay Portal artifacts directory. This is
the root directory extracted from the Liferay artifacts zip file. Or navigate to
your time-stamped Liferay Portal artifacts directory, if you built the artifacts
from source. 

4. Create a `build.[user name].properties` file (e.g.,
`build.jbloggs.properties`) in your `liferay-portal-maven-[version]` directory.
In the new properties file, specify values for the properties
`lp.maven.repository.id` and `lp.maven.repository.url`. These refer to your
repository's ID and URL, respectively.

    Here are some example property values:

        lp.maven.repository.id=liferay-releases
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases

    Note, if you created a repository in Nexus OSS, as demonstrated in the
    section *Managing Maven Repositories*, you can specify that repository's ID
    and URL.

5. To deploy to your release repository server, execute

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

    To verify your artifacts are deployed, navigate to the *Repositories* page
    of your Nexus OSS server and select your repository.

    ![Figure 8.4: You can easily navigate to your Liferay release repository in Nexus.](../../images/maven-select-repository2.png)

    Notice a window appears below displaying the Liferay artifacts now deployed
    to your repository.

![Figure 8.5: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

If you're working with Liferay CE, there's an alternative method of obtaining
the necessary Liferay Maven artifacts: you can let Maven download them
automatically. Let's see how. 

### Installing CE Artifacts from the Central Repository 

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

Next, we'll consider the benefits of using a Maven parent project with your
plugin projects. 

## Using a Parent Plugin Project 

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

1. Create a new directory for your parent project. For this example, we'll name
the directory `sample-parent-project`. You can place the directory anywhere on
your file system. 

2. Inside the `sample-parent-project` directory, create a `pom.xml` file and
insert the following lines: 

        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
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
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-impl</artifactId>
                    <version>6.1.20</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-service</artifactId>
                    <version>6.1.20</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-web</artifactId>
                    <version>6.1.20</version>
                    <type>war</type>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-bridges</artifactId>
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-java</artifactId>
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-taglib</artifactId>
                    <version>6.1.20</version>
                </dependency>
            </dependencies>

        </project>

    If you use this example POM, replace the `<version>` values with the version
    of Liferay applicable to the plugins you're developing. 

    <!--Change to 6.2 after release -->
    
3. Modify the values of the *properties* to match your Liferay environment.

    Fill in the `<liferay.auto.deploy.dir>...</liferay.auto.deploy.dir>` tags
    with the path of your Liferay bundle's `deploy` directory. This is the
    auto-deploy directory you will eventually copy your plugin to deploy on
    Liferay.

    Fill in the `<liferay.version>...</liferay.version>` tags with the version
    of Liferay you are using.

    Your POM's *properties* should look similar to the following:
    
        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
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

## Creating Liferay Plugins with Maven 

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that it's executable is in your path environment variable. 

---

Here's how you use Maven archetypes to generate a Liferay plugin project:

1. Open the command prompt and navigate to the parent directory in which you
want to create the plugin project. Archetype will create a sub-directory for
the plugin project you create.

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you haven't already
    created a parent project, you may want to consider creating one to share
    common project information. See section *Using a Parent Plugin Project* for
    details.

    ---

2. Execute the command

        mvn archetype:generate

    Archetype starts and lists the archetypes available to you. You'll be
    prompted to *choose* an archetype or *filter* archetypes by group / artifact
    ID. The output looks similar to the following text: 
    
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

3. To find the right Liferay archetype for your project, you can either scroll
up to find it or apply filters to narrow the set of results. Filtering on
*liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*, etc.)
can help you focus on more applicable Liferay archetypes. 

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

4. Choose an archetype by entering its number.

5. You're next prompted to choose the archetype version. Enter the number
   corresponding to the Liferay version for the archetype. However, you're not
   required to select the archetype version that corresponds with your Liferay
   instance; older archetype versions are compatible with updated Liferay
   bundles. 

    The snapshot below illustrates choosing the archetype version: 

    ![Figure 8.6: You're prompted by Maven Archetype to enter the archetype version.](../../images/maven-archetype-version.png)

6. Enter values for the *groupId*, *artifactId*, *version*, and *package*
coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 8.7: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).
    
6. Enter the letter `Y` to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/<archetype>`). If you
configured the mirror pointing to your public repository on Nexus, the
plugin is installed there. 

---
    
![Figure 8.8: Your archetype and its dependencies are now available in your local repository.](../../images/maven-public-plugin.png)

Following these steps, you can use Archetype to generate all your Liferay
plugins! 

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 

## Deploying Liferay Plugins with Maven 

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1. Make sure your parent POM's Liferay-specific properties specify the correct
Liferay version and your Liferay Portal's deploy directory path. 

    Here's an example of these properties using Liferay 6.1.20 and a deploy
    directory path of `E:\liferay-portal-6.1.20-ee-ga2\deploy`: 

        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
        </properties>

    If you haven't already created a parent plugin project, see *Using a Parent
    Plugin Project* for details. 

2. In your command prompt, navigate to your Liferay plugin project's directory. 

3. Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\ce-plugins-maven\sample-parent-project\sample-portlet\target\sample-portlet-1.0-SNAPSHOT.war
        ...
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

4. Deploy the plugin into your Liferay bundle by entering

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

5. If you're deploying the plugin to a release or snapshot repository,
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

    ![Figure 8.9: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    Since you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6. Deploy your plugin into your specified Nexus repository:

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

## Liferay Plugin Types to Develop with Maven 

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


### Creating a Portlet Plugin 

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to 
find the Liferay portlet archetypes more easily. 

---

#### Anatomy 

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

The `portlet-plugin/src/main/java/` directory holds the portlet's Java source
code (e.g., `com.liferay.sample.SamplePortlet.java`), and
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

#### Deployment 

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

#### More Information 

For detailed information on creating portlet plugins, see Chapter 3, 
[Portlet Development](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/portlet-developme-3). 

Next, let's run through a brief example for developing a theme plugin using
Maven.

### Developing Liferay Theme Plugins with Maven 

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

#### Creating a Theme Plugin 

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your theme project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to more easily
find the Liferay portlet archetypes. 

---

#### Anatomy 

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

#### Theme POM 

The theme plugin project POM has two additional properties: 

- `liferay.theme.parent`: Sets the parent theme. You can define almost any WAR
artifact as the parent using the syntax `groupId:artifactId:version`, or use the
core themes by specifying `_unstyled`, `_styled`, `classic`, or `control_panel`. 

- `liferay.theme.type`: Sets the template theme language. 

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

#### Deployment

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

#### More Information 

For more information on Liferay themes and its settings, see Chapter 4,
[Creating Liferay
Themes](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-liferay-them-7).

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

### Developing Liferay Hook Plugins with Maven 

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

#### Creating a Hook Plugin 

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:portlet`, to more easily find the
Liferay portlet archetypes. 

---

#### Anatomy 

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

#### Deployment 

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

#### More Information 

For detailed information on creating hooks, see Chapter 6,
[Hooks](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/hoo-4). 

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 

### Developing Liferay Layout Template Plugins with Maven 

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
It shall clasp a sainted kittenen whom the angels named Lenore II--"

In memory of the late, now cripsy Lenore II, let's create a layout template
plugin with Maven. 

#### Creating a Layout Template Plugin 

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section.

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your layout template project, you can filter on group ID
`liferay`, or even group ID / artifact ID combination `liferay:layout`, to find
the Liferay layout template archetypes.

---

#### Anatomy 

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

#### Deployment 

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

#### More Information 

For detailed information on creating layout templates, see Chapter 5, [Creating
Liferay Layout
Templates](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-creating-liferay-layout-templates-0). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 

### Developing More Liferay Plugins with Maven 

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

## Summary 

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
