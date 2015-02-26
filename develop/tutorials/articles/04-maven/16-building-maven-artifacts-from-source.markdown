# Building Liferay Maven Artifacts from Source [](id=building-maven-artifacts-from-source)

You can download the Liferay Maven artifacts if you're just interested in using
the artifacts for a specific release. However, if you'd like to use the very
latest Liferay CE Maven artifacts, you can build them from source. To build the
latest Liferay CE Maven artifacts from source, follow these steps:

1.  Navigate to your local Liferay Portal CE source project. If you don't
    already have a local Liferay Portal CE source project on your machine, you
    can fork the Liferay Portal CE Github repository, found at
    <http://github.com/liferay/liferay-portal>, and clone it to your machine.

2.  Create an `app.server.[user name].properties` file in your local Liferay
    Portal CE source project root directory. Specify the following properties in
    it:

        app.server.type=[your application server's type. Lookup your app server's type in the app.server.properties file in the same directory.]
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
    source project root directory and specify your GPG credentials and Maven
    repository information, similar to the properties in the following example
    listing: 

        gpg.keyname=[GPG key name]
        gpg.passphrase=[GPG passphrase]
        lp.maven.release.repository.id=liferay-releases
        lp.maven.release.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases
        lp.maven.snapshot.repository.id=liferay-snapshots
        lp.maven.snapshot.repository.url=http://localhost:8081/nexus/content/repositories/liferay-snapshots

    Of course, replace the values specified above with your own GPG credentials
    and Maven repository information.

    If you don't have GPG installed and don't have a public/private GPG key, you
    should visit [http://www.gnupg.org](http://www.gnupg.org) and install GPG
    before continuing. Once you've installed GPG, generate a GPG key by running
    `gpg --gen-key` and following the instructions. Once you've generated a GPG
    key, you can find your GPG keyname by running `gpg --list-keys`.
    
    +$$$

    **Note**: If you only plan to install the Liferay artifacts locally and not
    deploy them, the `releases.[user name].properties` is not required. 

    $$$

5.  Open a command prompt, navigate to your Liferay home directory, and build
    the Liferay Portal WAR file by running 

        ant -f build-dist.xml all zip-portal-war

6.  Deploy the Liferay artifacts to your Maven repository by running 

        ant -f build-maven.xml deploy-release-artifacts

    or

        ant -f build-maven.xml deploy-snapshot-artifacts

    If you want the Liferay artifacts to be installed locally but don't have a
    remote Maven repository or don't want the artifacts to be remotely deployed,
    you can run the installation target instead of the deploy target: `ant -f
    build-maven.xml install-release-artifacts` or `ant -f build-maven.xml
    install-snapshot-artifacts`. The target installs the Liferay artifacts you
    built to your local .m2 repository (e.g., to your `[USER_HOME]/.m2/`
    directory). 

+$$$

**Warning:** During the process of
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
		
$$$

Great! You now know how to build Liferay CE artifacts from your local portal
source code. 

Here are some related topics:

[Installing and Managing Liferay Maven Artifacts](/develop/tutorials/-/knowledge_base/6-2/managing-liferay-maven-artifacts)

[Using Maven from Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/using-maven-from-liferay-ide)
