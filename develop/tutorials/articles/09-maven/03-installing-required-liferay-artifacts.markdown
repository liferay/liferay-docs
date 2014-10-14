#  Installing Required Liferay Artifacts [](id=installing-required-liferay-artifacts)

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

+$$$

**Note:** The EE and CE zip files are a means to *install* the artifacts to a
Maven repository of your choice. In the next few sections, we'll demonstrate the
zip file and Central Repository installation options. 

$$$

Let's look at the manual process first, by downloading and installing Liferay
artifacts from a zip file. 

## Installing EE/CE Artifacts from a Zip File [](id=installing-ee-ce-artifacts-from-a-zip-file)

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

## Building CE Maven Artifacts from Source [](id=building-ce-maven-artifacts-from-source)

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

+$$$

**Warning:** During the process of packaging up the `javadoc.jar` files for your
Liferay artifacts, your machine may experience sluggish performance or an
insufficient amount of Java heap space. There are two solutions to this problem:
 
- **Increase the memory available for the Javadoc packaging process:**
  Navigate to `[Liferay home]/build.xml` and search for the `javadoc` target.
  Find the `maxmemory` property and increase it as desired.
- **Skip the Javadoc packaging process:** Navigate to
  `[Liferay home]/build-maven.xml` and find the `prepare-maven` target. Within
  this target, comment out the call to the `jar-javadoc` target, like below:

		<!-- <antcall target="jar-javadoc" /> -->
		
$$$

Next, we'll show you how to install the required Liferay release artifacts to
your repositories. These steps are applicable for installing artifacts that you
downloaded and extracted from a zip file, and for installing artifacts that you
built from source. 

### Installing Artifacts to a Repository [](id=installing-artifacts-to-a-repository)

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

### Deploying Artifacts to a Repository [](id=deploying-artifacts-to-a-repository)

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

## Installing CE Artifacts from the Central Repository [](id=installing-ce-artifacts-from-the-central-repository)

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
