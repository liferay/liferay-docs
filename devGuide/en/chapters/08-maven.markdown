# Developing plugins using Maven [](id=developing-plugins-using-maven)

As an alternative to developing plugins using Liferay's Ant-based Plugins SDK,
you can leverage the Apache Maven build management framework. To mention just a
few of its features, Maven offers a simple build process, a project object
model, a project life cycle, and a dependency management system. Maven's core
installation is light-weight providing core plugins for compiling source code
and creating distributions, but the abundance of non-core plugins for Maven lets
you extend it easily for customizations.

Many developers are switching from Ant to Maven because it offers a common
interface for project builds. Maven's universal directory structure makes it
easier for developers to understand each other's projects more quickly.
Developers follow a simple process to build, install, and deploy project
artifacts.

Maven uses a *project object model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. A project's POM describes its
directories, required plugins, build sequence, and dependencies. The POM is like
a blueprint of your project. Maven only needs the POM as a project's sole
descriptive declaration. You create the `pom.xml` file, invoke the build
process, and Maven does the rest, downloading your project's inferred
dependencies and building your project artifacts! To get familiar with Maven's
project object model, read Sonatype's documentation for it at
[http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html).

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--its POM. Understanding a Maven project can
be much more simple than, for instance, understanding an Ant-based project's
various build files. Maven forces projects to conform to a standard build
process, whereas Ant projects can be built differently from project to project.
Also, Maven provides an easy way to share artifacts (e.g., JARs, WARs, etc.)
across projects via public repositories.

However, you may find the Maven project structure too restrictive or you may
find it cumbersome to reorganize your project to work with Maven. Maven is
primarily intended for Java-based projects, so you may find it difficult to
manage your project's non-Java source code. We encourage you to consider Maven's
advantages and disadvantages in deciding what is best for managing your
projects. For an in-depth look at Maven's features and what Maven can do for
you, visit *Maven: The Complete Reference* by Sonatype, Inc. at
[http://www.sonatype.com/books/mvnref-book/reference/](http://www.sonatype.com/books/mvnref-book/reference/)
and continue reading this chapter.

You'll be happy to know Liferay provides Maven archetypes to help you build
various plugins including Liferay portlets, themes, hooks, layout templates, web
plugins and more. You can also install and deploy Liferay artifacts to your
repositories. We'll dive into all these topics in this chapter, so buckle up
and prepare to be Mavenized!

## Installing Maven [](id=installing-maven)

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi) and
install it where you wish. We recommend putting your Maven installation's `bin`
directory in your system's path, so you can run the Maven executable (*mvn*)
easily from your command prompt.

Next, let's learn about the types of repositories you can use with Maven
projects.

## Using Maven repositories [](id=using-maven-repositories)

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! With Maven, you can install your artifacts to your
machine's local repository and even deploy them to remote repositories for
sharing privately with your team or publicly for general consumption. Your
*local* repository holds downloaded artifacts and artifacts you install to it.
*Remote* repositories are for sharing artifacts either privately within your
development team or publicly to the community at large. To learn more about
using artifact repositories see
[http://maven.apache.org/guides/introduction/introduction-to-repositories.html](http://maven.apache.org/guides/introduction/introduction-to-repositories.html).

With Maven, you can also configure a proxy server to mediate your requests to
public Maven repositories and to cache artifacts locally. Using a local
proxy/repository helps you build projects faster and more reliably, as accessing
remote repositories is slower and remote repositories may sometimes be
unavailable. Most Maven proxies can also host private repositories used for
hosting your private artifacts. If you're interested in running your repository
behind a proxy, see
[http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html](http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html).

Now that you've been introduced to local and remote Maven repositories and proxy
servers, let's consider using a repository management server to create and
manage your Maven repositories.

### Managing Maven repositories [](id=managing-maven-repositories)

If you want to share Liferay artifacts and plugins with teammates or want to
manage your repositories using a GUI, you can use Nexus OSS. It is a Maven
repository management server that facilitates creating and managing release
servers, snapshot servers, and proxy servers. If you are not interested in using
Nexus as a repository management server, you can skip this section.

Let's create a Maven repository using Nexus OSS. You can download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
instructions at
[http://www.sonatype.com/books/nexus-book/reference/\_installing_nexus.html](http://www.sonatype.com/books/nexus-book/reference/\_installing_nexus.html)
for installing it and starting it up.

To create a repository using Nexus, do the following:

1. Open your browser to the location of your Nexus repository server (e.g.,
[http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in.

2. Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

    ![Figure 8.1: Adding a repository to hold your Liferay artifacts is easy
    with Nexus OSS.](../../images/maven-nexus-create-repo.png)

    Note: To learn more about each type of Nexus repository, read Sonatype's
    *Managing Repositories* at [http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3. Give your repository appropriate properties based on the access you want to
provide its artifacts. Since we will be installing release version artifacts
into this repository, specify *Release* as its repository policy. Below are some
example repository property values:

    - *Repository ID:* `liferay-releases`
    - *Repository Name:* `Liferay Release Repository`
    - *Provider:* `Maven2`
    - *Repository Policy:* `Release`

Congratulations on creating a Maven repository accessible from your Nexus OSS
repository server!

Let's take it one step further and create a Maven repository to hold snapshots
of the Liferay plugins we create. The steps for creating a *snapshot* repository
are almost identical to the steps for creating a *release* repository. The only
difference is that we'll specify *Snapshot* as its repository policy:

1. Go to your Nexus repository server in your browser.

2. Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

3. Specify repository properties like the following:

    - *Repository ID:* `liferay-snapshots`
    - *Repository Name:* `Liferay Snapshot Repository`
    - *Provider:* `Maven2`
    - *Repository Policy:* `Snapshot`

Voila! You not only have a repository for your Liferay releases (i.e.,
*liferay-releases*), you also have a repository for your Liferay plugin
snapshots (i.e., *liferay-snapshots*).

Let's configure your new repository servers in your Maven environment so you
can install artifacts to the them. 

### Configuring local Maven settings  [](id=configuring-local-maven-settings)

In order to use your repository servers and/or any repository mirrors, you must
specify them in your Maven environment settings. With regards to your
repositories, the settings provide a means for Maven to find each repository and
access them for retrieving and installing artifacts.

---

 ![note](../../images/tip-pen-paper.png)**Note:** You're only required to
 configure a repository server if you're installing downloaded Liferay CE/EE
 artifacts from a zip file or if you want to share artifacts (e.g., Liferay
 artifacts and/or your plugins) with others. If you are automatically installing
 Liferay CE artifacts from the Central Repository and are not interested in
 sharing artifacts, you don't need a repository server specified in your Maven
 settings.

 However, configuring a mirror in your Maven settings is a best practice to
 consider. For more information on mirrors and their purpose, see Maven's guide
 on mirrors at
 [http://maven.apache.org/guides/mini/guide-mirror-settings.html](http://maven.apache.org/guides/mini/guide-mirror-settings.html).

---

To configure your Maven environment to access your *liferay-releases* repository
server, do the following:

1. Navigate to your `${USER_HOME}/.m2/` directory.

2. Open your `settings.xml` file, or create it if it doesn't already exist.

3. Input the settings for your repository servers. Here are contents from a
`settings.xml` file that has *liferay-releases* and *liferay-snapshots*
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

You now have your repositories configured and ready to receive Liferay Maven
artifacts you'll download and Liferay plugin artifacts you'll create!

Now, let's get the Liferay artifacts you'll need to create your plugins.

##  Installing required Liferay artifacts [](id=installing-required-liferay-artifacts)

In order to create Liferay plugins using Maven, you'll need Liferay's required
archives (e.g., required JAR and WAR files). Good news! Liferay provides them as
Maven *artifacts*. Depending on whether you are building plugins for Liferay EE
or Liferay CE, here are options to consider in obtaining and installing the
Liferay Maven artifacts:

- Install EE/CE artifacts from a zip file manually
- Install CE artifacts from the Central Repository automatically

If you are using CE, the CE artifacts can be retrieved automatically during the
Maven build process. Or you can download the CE artifacts zip file manually, if
you like.

If you are using EE, you must manually download the EE artifacts zip file, as
the required EE artifacts are not available from the Central Repository.

Note, the EE and CE zip files provide a means to *install* the artifacts to a
Maven repository of your choice. In the next few sections, we'll show you how to
execute both the zip file and Central Repository installation options.

### Installing EE/CE artifacts from a zip file [](id=installing-artifacts-from-a-zip-file)

In this section, you'll learn how to download and install the Liferay EE and CE
artifacts from the zip files that Liferay provides for their respective
releases. Let's cover downloading the Liferay EE artifacts first.

The Liferay EE artifacts package can be downloaded by visiting Liferay's
Customer Portal. To access the Liferay Customer Portal and begin downloading
Liferay Maven EE artifacts, follow these steps:

1. Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2. Go to *Places* &rarr; *Customer Portal*.

3. Select *Liferay Portal* from the *Downloads* panel.

4. Inside *Filter by:*, select the appropriate Liferay version in the first
field and select the *Development* value in the second field.

    ![Figure 8.2: You can download the Liferay Maven EE artifacts from the
    Liferay Customer Portal.](../../images/maven-customer-portal.png)

5. Click *Download* from under the desired *Liferay Portal \<Version\> Maven*.

    The Liferay Maven EE artifacts package downloads to your machine.

The Liferay CE artifacts package can be downloaded from SourceForge:

1. Open your browser to *Liferay Portal* on SourceForge at
[http://sourceforge.net/projects/lportal/files/Liferay%20Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2. Select the version information of the Liferay Maven artifacts you want. For
example, if you want Maven artifacts for Liferay Portal 6.1.1 CE GA2, select
version *6.1.1 GA2*.

    ![Figure 8.3: After selecting the Liferay version, simply select the Liferay
    Portal Maven zip file to download.](../../images/maven-select-download.png)

3. Select the desired zip file. The zip files use naming convention
*liferay-portal-maven-\<version\>-\<date\>.zip*.

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay EE/CE artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories.
We'll show you how to install these required Liferay release artifacts to your
*liferay-releases* repository.

#### Installing downloaded artifacts to a repository [](id=installing-downloaded-artifacts-to-a-repository)

Let's *install* the Liferay release artifacts to your local Maven repository for
sharing with your team.

1. Using your command prompt, navigate to the *liferay-portal-maven-\<version\>*
directory. This is the root directory extracted from the Liferay artifacts zip
file.

2. To install the artifacts to your local repository, execute:

        ant install

    Your console shows output from the artifacts being installed from the
    Liferay Maven package into your local repository, typically located in your
    `${USER_HOME}/.m2/repository` directory.

Your local repository now contains the Liferay artifacts required to build
Liferay plugins. Wasn't that easy?

Next, let's *deploy* the Liferay artifacts to a release repository server.

#### Deploying downloaded artifacts to a repository [](id=deploying-downloaded-artifacts-to-a-repository)

You may find it worthwhile to share your Liferay artifacts with teammates.

Here is how you do it:

1. Make sure you have created a repository server to hold the Liferay Maven
artifacts. See the *Managing Maven repositories* section for instructions on
creating a repository named *liferay-releases*.

2. Check that the repository to hold your Liferay artifacts is specified as a
server in your Maven `settings.xml` file. See the *Configuring local Maven
settings* for instructions on adding an entry for the server.

    Here is an example setting for repository server named *liferay-releases*:

        <servers>
            ...
            <server>
                <id>liferay-releases</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
            ...
        </servers>

3. Using your command prompt, navigate to the *liferay-portal-maven-\<version\>*
directory. This is the root directory extracted from the Liferay artifacts zip
file.

4. Create a `build.${USER_NAME}.properties` file (e.g.,
`build.jblogs.properties`) in your *liferay-portal-maven-\<version\>* directory.
In that properties file, specify values for `lp.maven.repository.id` and
`lp.maven.repository.url` properties. These properties refer to your
repository's ID and URL, respectively.

    Here are some example property values:

        lp.maven.repository.id=liferay-releases
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases

    Note, if you created a repository in Nexus OSS, as demonstrated in section
    *Managing Maven repositories*, you can specify that repository's ID and URL.

3. To deploy to your release repository server, execute:

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

    To verify your artifacts are deployed, navigate to the *Repositories* page
    of your Nexus OSS server and select your repository.

    ![Figure 8.4: You can easily navigate to your Liferay release repository in
    Nexus.](../../images/maven-select-repository2.png)

    Notice a window appears below displaying the Liferay artifacts now deployed
    to your repository.

    ![Figure 8.5: Your repository server now provides access to your Liferay
    Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates.

If you are working with Liferay CE, the other installation option you have is to
rely on Maven to download the Liferay Maven CE artifacts automatically. We'll
cover that next.

### Installing CE artifacts from the Central Repository [](id=installing-artifacts-from-the-central-repository)

Liferay offers an option for obtaining and installing Liferay CE artifacts,
automatically. The CE artifacts are publicly available on the *Central
Repository*, located at
[http://search.maven.org/#search|ga|1|liferay%20maven](http://search.maven.org/#search|ga|1|liferay%20maven),
and are updated with each Liferay release (e.g., 6.1.0, 6.1.10, 6.1.20, etc.).
The first time you use Maven to compile a Liferay plugin project, if the
required Liferay CE artifacts are not found in your local repository and are not
found in any of your configured repository servers, Maven automatically
downloads the artifacts from the Central Repository into your local repository.
You'll get to see that in action when you package your Liferay CE plugins.

Next, we'll consider the benefits of using a Maven *parent* project with your
plugin projects.

## Using a parent plugin project [](id=using-a-parent-plugin-project)

Maven supports project inheritance through the use of *parent* projects.
Project inheritance comes in handy when you have properties common to multiple
projects--like when you are working on multiple Liferay plugin projects.

If you don't already have a Maven project for developing your Liferay plugins or
if you are curious about using a parent project to organize your Liferay plugin
projects, we'll show you how. Otherwise, just follow along as we'll use this
parent project paradigm in demonstrating how to use Liferay artifacts and
develop Liferay plugins. See Maven's documentation on project inheritance found
at
[http://maven.apache.org/pom.html#Inheritance](http://maven.apache.org/pom.html#Inheritance).

Let's specify some general settings you'll need to build plugins. First, we'll
create a *parent* project. The parent project is similar to the project root of
the Liferay Plugins SDK. The parent project's `pom.xml` file can specify
information to be used by the plugin projects that refer to it. You can always
specify information in each plugin's POM, but you will often find it convenient
to use the parent project's POM for sharing common information.

Let's create a parent project named *sample-parent-project*:

1. Create a new directory for your parent project. For this example, we'll name
the directory `sample-parent-project`. Your parent project can be placed
anywhere on the file system and still interact with your Liferay instance.

2. Inside the `sample-parent-project` directory, create a `pom.xml` file and
insert the following:

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

    You can use this example POM replacing the version values with the version
    of Liferay applicable to the plugins you are developing.

3. Modify the values of the *properties* to match your Liferay environment.

    - Fill in the `<liferay.auto.deploy.dir>...</liferay.auto.deploy.dir>` tags
    with the path of your Liferay bundle's *deploy* directory. This is the
    auto-deploy directory you will eventually copy your plugin to deploy on
    Liferay.

    - Fill in the `<liferay.version>...</liferay.version>` tags with the version
    of Liferay you are using.

    Your POM's *properties* should look similar to the following:
    
        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
        </properties>

Because you've specified your Liferay instance's *deploy* directory in the POM,
Maven knows exactly where to deploy your plugin artifacts.

Your parent now specifies common dependencies on required Liferay Maven
artifacts. All of your parent project's modules (i.e., projects that refer to
this parent) can leverage these dependencies.

Note, you could just as easily include such dependencies in the POM of each of
your plugin projects, but specifying them in a parent project makes them
accessible to child projects through inheritance.

Now that you know how to specify dependencies on required Liferay artifacts,
let's use the *archetypes* Liferay provides for creating Liferay plugin
projects.

## Creating Liferay plugins with Maven [](id=creating-liferay-plugins-with-maven)

Liferay offers many archetypes to help you create Maven projects for multiple
types of Liferay plugins, including portlet, theme, hook, and layout template
plugins. And, of course, we provide archetypes for each of these plugin types
for various versions of Liferay.

Let's go through an example of creating a Liferay portlet project using
*Archetype*, Maven's project templating toolkit. You'll be able to apply these
Archetype tool steps to generate all kinds of Liferay plugin projects.

---

 ![note](../../images/tip-pen-paper.png)**Note:** Make sure Maven is installed and that
 its executable is in your path environment variable. 

---

Here is how you use Archetype to generate a Liferay plugin project:

1. Open the command prompt and navigate to the parent directory in which you
want to create the plugin project. Archetype will create a sub-directory for
the plugin project you create.

    Note, if you haven't already created a parent project, you may want to
    consider creating one to share common project information. See section
    *Using a parent plugin project* for details.

2. Execute:

        mvn archetype:generate

    Archetype starts up and lists all kinds of archetypes available to you.
    Lastly, it prompts you to *choose* an archetype or *filter* on archetypes by
    group / artifact ID. The output looks similar to the following:
    
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

3. To find the Liferay archetype you want, you can either scroll up to find it
or apply filters to narrow the set of results. Filtering on "liferay" as your
group ID, and a plugin type ("portlet", "hook", "theme", etc.) can help you find
applicable Liferay archetypes.

    For example, you can enter "liferay:portlet" to get a listing of Liferay
    portlet archetypes like below:

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

5. Next, you are prompted to choose the archetype version. Enter the number
corresponding to the Liferay version for the archetype. You're not required to
select the archetype version that corresponds with your Liferay instance per
se, as older archetype versions are compatible with updated Liferay bundles.

    The snapshot below illustrates choosing the archetype version:

    ![Figure 8.6: You are prompted by Maven Archetype to input the version of
    the archetype to generate.](../../images/maven-archetype-version.png)

6. Enter values for the *groupId*, *artifactId*, *version*, and *package*
coordinates (properties) of your project. For more information on defining Maven
coordinates, see
[http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).

    Here are some example values for a sample portlet:

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 8.7: When creating your portlet plugin, you must enter your
    *groupId*, *artifactId*, *version*, and *package*
    properties.](../../images/maven-portlet-plugin-settings.png)

6. Enter the letter *Y* to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory, equipped
    with a new `pom.xml` file and source code.

    Note, the archetype file is downloaded and installed automatically to your
    local repository (i.e.,
    `.m2/repository/com/liferay/maven/archetypes/<archetype>`). Likewise, if you
    configured the mirror pointing to your public repository on Nexus, the
    plugin is installed there as well.
    
    ![Figure 8.8: Your archetype and its dependencies are now available in
    your local repository.](../../images/maven-public-plugin.png)

You can use Maven's Archetype tool to generate other types of plugins in the
same manner.

A plugin project generated from a Liferay archetype comes equipped with a POM
that is ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent.

When you're finished creating your plugin, you can package and deploy your
project to a specified Liferay instance. You can even install and deploy the
individual plugin to a remote repository.

Next, we'll go through some brief examples to demonstrate how to deploy your
plugins to Liferay Portal using Maven.

## Deploying Liferay plugins with Maven [](id=deploying-liferay-plugins-with-maven)

Maven makes it easy to deploy plugins to a Liferay Portal instance. We'll show
you how.

1. Make sure your parent POM's Liferay specific properties specify the correct
Liferay version and your Liferay Portal's deploy directory path.

    For example,

        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
        </properties>

    If you haven't already created a parent plugin project, see *Using a parent
    plugin project* for details.

2. Open your command prompt to the Liferay plugin project you want to deploy.

3. Package your plugin.

        mvn package

    Your command output should look similar to the following:

        [INFO] Building war:
        E:\ce-plugins-maven\sample-parent-project\sample-portlet\target\sample-portlet-1.0-SNAPSHOT.war
        ...
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

4. Deploy your plugin into your Liferay bundle.

        mvn liferay:deploy

    The command output should look similar to the following:

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

    In the meantime, your Liferay console output should show your plugin
    deploying.

        INFO: Deploying web application directory [liferay version]\[tomcat version]\webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from queue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for sample-portlet

    ---
    
     ![note](../../images/tip-pen-paper.png)**Note:** If you get the following
     error executing `mvn liferay:deploy`, check to see if you're executing the
     command from your plugin's directory (e.g. `sample-portlet`).
    
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    ---

5. If you're interested in deploying the plugin to a release or snapshot
repository, specify the repository in a distribution management section of your
plugin's `pom.xml`.

    For example,

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

    To get you started with the proper contents for your
    `<distributionManagement>` element, select the *Summary* tab for each of your
    repositories.

    ![Figure 8.9: Select the *Summary* tab of your repository to see how to
    specify it for distribution management in your plugin's
    POM.](../../images/maven-repository-summary.png)

Note, since we created the plugin as a snapshot, we must deploy it to a snapshot
repository. You can also deploy a plugin as a release, but the plugin's POM
*must* specify a valid release version (e.g., `<version>1.0</version>`), *not* a
snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`).

---

 ![note](../../images/tip-pen-paper.png)**Note:** You'll use the following three
 build phases in developing plugins with Maven:

 - Maven's *compile* phase results in explicit dependencies being downloaded to
 your local repository (i.e., `.m2\repository\com\liferay\portal`)
 - Maven's *package* phase results in the plugin's inferred dependencies
 being downloaded to your local repository. (`.m2\repository`).
 - Maven's *install* phase results in your plugin being installed to your local
 repository.

---

Now that you've deployed a plugin using Maven, let's take a look at some of the
types of Liferay plugins you can develop using Liferay Maven archetypes.

## Liferay plugin types to develop with Maven [](id=liferay-plugin-types-to-develop-with-maven)

In this section, we'll highlight the various Liferay plugin types you can
develop with Maven, how you create them with Maven, and compare them with
plugins you can create with the Liferay Plugins SDK. Throughout this section,
we'll refer to previous sections applicable to creating and deploying these
plugin types. And we'll also refer to other sections of this document that
explain how you develop each type of plugin, regardless of development
environment.

The sub-sections that follow cover:

- Developing Liferay *portlet* plugins with Maven

- Developing Liferay *theme* plugins with Maven

- Developing Liferay *layout template* plugins with Maven

- Developing Liferay *hook* plugins with Maven

- Developing other Liferay plugins with Maven

Let's dive into portlet plugins next.

### Developing Liferay portlet plugins with Maven [](id=developing-liferay-portlet-plugins-with-maven)

One of the plugins you can create using Maven is a Liferay portlet.

**Creating a portlet plugin**

To create a Liferay portlet plugin project, follow the *Creating Liferay plugin
projects with Maven* section.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: As you use Maven's Archetype
 tool to generate your portlet project, you can filter on group ID "liferay", or
 even group ID / artifact ID combination "liferay:portlet", to find the Liferay
 portlet archetypes.

---

**Anatomy**

A portlet project created from the
*com.liferay.maven.archetypes:liferay-portlet-archetype* has the following
anatomy:

- *portlet-plugin*/
    - src/
        - main/
            - java/ \* Holds Java source (e.g., 
              `com.liferay.sample.SamplePortlet.java`)
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

There are a couple of directory structure differences between plugin projects
created using Liferay Maven archetypes and those created using the Liferay
Plugins SDK. The following table illustrates the differences in location of the
Java source code and web source code.

Location    | &nbsp;Maven project | &nbsp;Plugins SDK project |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot` |

To view the anatomy of a portlet developed by Ant, visit the [Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-portl-4)
section in this guide.

**Deployment**

To deploy your portlet plugin, follow the instructions in the *Deploying Liferay
plugins with Maven* section.

Congratulations! You have successfully created a Liferay portlet plugin using
Maven.

**More information**

For detailed information on creating all kinds of portlets, see the [*Portlet
Development*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/portlet-developme-3)
chapter of this guide.

Next, let's run through a brief example for developing a theme plugin using
Maven.

### Developing Liferay theme plugins with Maven [](id=developing-liferay-theme-plugins-with-maven)

Imagine yourself sitting in a luxurious chair next to a bustling fire on a cold
winter's night (in front of your computer, of course). Now imagine yourself
sitting on a cold hard wooden chair inside a cubicle (again on your computer).
These two descriptions paint two very different pictures, but describe what
you're doing all the same. This "change in scenery" for your portal can really
set the mood for your users. Luckily, you can develop your own theme plugin
using Maven so your portal has a lasting effect on anyone who visits it.

**Creating a theme plugin**

The process of creating a theme plugin is similar to the previous portlet we
created. Our theme plugin demonstration also assumes you have already created
the `sample-parent-project` and `pom.xml`.

To create a Liferay theme plugin project follow the *Creating Liferay plugin
projects with Maven* section.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: As you use Maven's Archetype
 tool to generate your theme project, you can filter on group ID "liferay", or
 even group ID / artifact ID combination "liferay:theme", to find the Liferay
 theme archetypes.

---

**Anatomy**

A theme project created from the
*com.liferay.maven.archetypes:liferay-theme-archetype* has the following
anatomy:

- sample-theme/
	- pom.xml
	- src/
		- main/
			- resources/
			- webapp/
				- WEB-INF/
					- liferay-plugin-package.properties
					- web.xml
				- css/ \* optionally add this directory for CSS customizations
				- images/ \* optionally add this directory for customizations
				- js/ \* optionally add this directory JavaScript customizations
				- templates/ \* optionally add this directory template customizations

There is a directory structure difference between plugin projects created
using Liferay Maven archetypes and those created using the Liferay Plugins SDK.
The following table illustrates this difference.

Location    | &nbsp;Maven project | &nbsp;Plugins SDK project |
----------- | ----------------- | --------------------- |
customizations | `src/main/webapp` | `docroot/_diffs` |

Add your customizations in `src/main/webapp` following the same structure as you
would in `docroot/_diffs` of the Plugins SDK. So, for example, your `custom.css`
would go to `src/main/webapp/css/custom.css`.

To view the anatomy of a theme developed by Ant, visit the [*Anatomy of a
Theme*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-the-4)
section in this guide.

**Theme POM**

The theme plugin project POM has two additional properties:

- *liferay.theme.parent* - Sets the parent theme. This property allows you to
define basically any WAR artifact as the parent using the syntax
*groupId:artifactId:version*. Or you can use the core themes by specifying
either *_unstyled*, *_styled*, *classic*, or *control_panel*.

- *liferay.theme.type* - Sets the template theme language

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

**Deployment**

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
plugins with Maven* section.

Note, on executing the *package* goal, a WAR file just like the Maven WAR type
project is created. Also, the parent theme is downloaded, copied, and overlaid
with your theme customizations on top of it. And a thumbnail image is created
the theme output to the *target* directory. See
`target/<theme>/images/screenshot.png` in your theme project.

**More information**

For more information on Liferay themes and its settings, visit Liferay's
[*Creating Liferay
Themes*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-liferay-them-7)
chapter of this document.

You have successfully developed a Liferay theme using Maven. Let's look at
developing hook plugins next.

### Developing Liferay hook plugins with Maven [](id=developing-liferay-hook-plugins-with-maven)

Hooks are the preferred plugin to use in customizing Liferay's core features.
You can create hook plugins in much the same way that you do portlet plugins in
Maven. Let's take a look.

**Creating a hook plugin**

To create a Liferay hook plugin project, follow the *Creating Liferay plugin
projects with Maven* section.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: As you use Maven's Archetype
 tool to generate your hook project, you can filter on group ID "liferay", or
 even group ID / artifact ID combination "liferay:hook", to find the Liferay
 hook archetypes.

---

**Anatomy**

A hook project created from the
*com.liferay.maven.archetypes:liferay-hook-archetype* has the following
anatomy:

- *hook-plugin*/
    - src/
        - main/
            - java/ \* Optionally add to hold Java source
            - webapp/
                - WEB-INF/
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

There are couple of directory structure differences between plugin projects
created using Liferay Maven archetypes and those created using the Liferay
Plugins SDK. The following table illustrates the difference in location of the
Java source code and web source code.

Location    | &nbsp;Maven project | &nbsp;Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the anatomy of a hook developed by Ant, visit the *Anatomy of a
Hook* section of the [*Creating a Hook*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-a-ho-4)
chapter in this guide.

**Deployment**

To deploy your hook plugin, follow the instructions in the *Deploying Liferay
plugins with Maven* section.

**More information**

For detailed information on creating all kinds of hooks, see the [*Hooks*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/hoo-4)
chapter of this guide.

OK, you've learned how to create hook plugins with Maven. Let's round things out
by considering how to develop a layout template as well.

### Developing Liferay layout template plugins with Maven [](id=developing-liferay-layout-template-plugins-with-maven)

You can create your own layout templates to enhance the flow of portlets on your
page and to embed commonly used portlets. Let's consider how to create a layout
template plugin with Maven.

**Creating a layout template plugin**

To create a Liferay layout template plugin project follow the *Creating Liferay
plugin projects with Maven* section.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: As you use Maven's Archetype
 tool to generate your layout template project, you can filter on group ID
 "liferay", or even group ID / artifact ID combination "liferay:layout", to find
 the Liferay layout template archetypes.

---

**Anatomy**

A layout template project created from the
*com.liferay.maven.archetypes:liferay-layouttpl-archetype* has the following
anatomy:

- *layouttpl-plugin*/
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

There is a directory structure difference between plugin projects created
using Liferay Maven archetypes and those created using the Liferay Plugins SDK.
The following table illustrates this difference.

Location   | &nbsp;Maven project | &nbsp;Plugins SDK project |
---------- | ----------------- | --------- |
Web source | `src/main/webapp` | `docroot` |


To view the anatomy of a layout template developed by Ant, visit the [*Anatomy
of a Layout Template
Project*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-anatomy-of-a-layout-template-0)
section in this guide.

**Deployment**

To deploy your layout template plugin, follow the instructions in the *Deploying
Liferay plugins with Maven* section.

**More information**

For detailed information on creating all kinds of layout templates, see the
[*Creating Liferay Layout
Templates*](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-creating-liferay-layout-templates-0)
chapter of this guide.

You've proved yourself at developing yet another plugin type with Maven. Way to
go! In the next section we'll mention some more plugin types for which Liferay
provides Maven archetypes.

### Developing more Liferay plugins with Maven [](id=developing-more-liferay-plugins-with-maven)

Just when you thought the archetype list was complete, the Liferay team has come
up with even more plugins that you can create using Maven archetypes.

Here are just a few more exciting archetypes available to you:

- Liferay ServiceBuilder portlets
- Liferay webs
- Liferay Ext
- JSF Portlet Archetype
- ICEFaces Portlet Archetype
- PrimeFaces Portlet Archetype
- Liferay Faces Alloy Portlet Archetype
- Liferay Rich Faces Portlet Archetype

Plus, there are some more Maven *goals* available:

- DBBuilder - build-db goal allows you to execute the DBBuilder to generate SQL
files
- SassToCSSBuilder - build-css goal precompiles SASS in your css and this goal
has been added to theme archetype

You now have plenty of archetypes at your disposal!

## Summary [](id=summ-42)

Developing plugins using Maven follows a uniform process which includes
generating the archetypes and selecting the options you're interested in. Then,
you can customize the archetype to your liking. Using Maven to develop plugins
offers an easy and effective way to customize your Liferay Portal.

Now that you have Maven configured locally, have the required Liferay Maven
artifacts, and have first-hand experience creating Liferay plugins with Maven,
you're ready to create all kinds of Liferay plugins based on Liferay's plugin
archetypes. At this point, consider yourself completely Mavenized!

You have the Liferay Plugins SDK and Maven build framework to use in developing
your plugins. How about an IDE to add to your toolbox--Liferay Developer Studio!
We'll cover Liferay Developer Studio and Liferay IDE next.
