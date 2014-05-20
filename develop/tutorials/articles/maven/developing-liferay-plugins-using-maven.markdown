# Developing Liferay Plugins Using Maven

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

<!--
The following info on our Maven coverage seems useful but we need to decide
where it should go. Should it go in the first of a series of related tutorial
articles? (But tutorial articles aren't arranged sequentially.) - JR
-->

<!--
Quoth the Maven, "Let us proceed undaunted in exploration of these topics:" 

- Installing Maven 
- Using Maven Repositories
- Installing Required Liferay Artifacts 
- Using a Parent Plugin Project
- Creating Liferay Plugins with Maven
- Deploying Liferay Plugins with Maven
- Liferay Plugin Types to Develop with Maven
-->

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

In this article, we discuss installing and configuring Maven and Nexus. Nexus is
a Maven repository management server. Using Nexus for Maven repository
management is convenient but not required. Once you've installed and configured
Maven (and, optionally, Nexus) in your development environment, you'll be ready
to install the Liferay plugin artifacts which enable you to develop Liferay
plugins with Maven.

## Installing Maven [](id=installing-maven-liferay-portal-6-2-dev-guide-02-en)

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
`$PATH`, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

## Understanding Maven Repositories [](id=using-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! Maven lets you install your artifacts both to local and
remote repositories. This means that you can share Maven repositories privately
with your team or with the public. Your *local* repository holds your downloaded
artifacts and those artifacts you install to it. *Remote* repositories are for
sharing artifacts either privately (e.g., within your development team) or
publicly. To learn more about using artifact repositories see
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

## Managing Maven Repositories [](id=managing-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. You might find Sonatype Nexus to be a
valuable tool for managing your repositories. It's a Maven repository management
server that facilitates creating and managing release servers, snapshot servers,
and proxy servers. Release servers hold software that has met the software
provider's criteria for planned features and quality. Snapshot servers hold
software that is in a state of development. If you're not interested in using
Nexus as a repository management server, feel free to skip this section. 

To create a Maven repository using Nexus, first download Nexus from
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

It's also useful to create a Maven repository to hold snapshots of each Liferay
plugin you create. Creating a *snapshot* repository is almost identical to
creating a *release* repository. The only difference is that we'll specify
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
snapshots (i.e., `liferay-snapshots`). 

Let's configure your new repository servers in your Maven environment so you can
install artifacts to them. 

## Configuring Local Maven Settings [](id=configuring-local-maven-settings-liferay-portal-6-2-dev-guide-02-en)

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

## Next Steps

Next, let's install the Liferay artifacts you'll need to create your plugins
with Maven.

[Installing Liferay Maven Artifacts](http://www.liferay.com)
