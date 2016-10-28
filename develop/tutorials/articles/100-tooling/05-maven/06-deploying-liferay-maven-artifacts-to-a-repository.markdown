# Deploying Liferay Maven Artifacts to a Repository [](id=deploying-liferay-maven-artifacts-to-a-repository)

Deploying artifacts to a remote repository is important if you intend to share
your Maven projects with others. First, you must have a remote repository that
can hold deployed Maven artifacts. If you do not currently have a remote
repository, see the
[Creating a Maven Repository](/develop/tutorials/-/knowledge_base/7-0/creating-a-maven-repository)
tutorial to learn how you can set up a Nexus repository. Also make sure your
`[USER_HOME]/.m2/settings.xml` file specifies your remote repository's ID,
user name, and password.

To deploy to a remote repository, your Liferay module should be packaged using
Maven. Maven provides a packaging command that creates an artifact (JAR) that
can be easily deployed to your remote repository. You'll learn how to do this
with a Liferay portlet module.

Once you've created a deployable artifact, you'll configure your module project
to communicate with your remote repository and use Maven's deploy command to
send it on its way. Once your module project resides on the remote repository,
other developers can configure your remote repository in their projects and set
dependencies in their project POMs to reference it.

To follow this tutorial, you'll need a Liferay module built with Maven. For
demonstration purposes, this tutorial uses the `portlet.ds` sample module
project. To follow along with this module, download the
[portlet.ds](https://dev.liferay.com/documents/10184/656312/portlet.ds.zip)
Zip. 

1.  Create a folder anywhere on your machine to serve as the parent folder
    for your Liferay modules. Unzip the `portlet.ds` module project into that
    folder.

2.  Create a `pom.xml` file inside this folder. Copy the following logic
    into the parent POM:

        <?xml version="1.0" encoding="UTF-8"?>
        <project
            xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
        >

            <modelVersion>4.0.0</modelVersion>
            <groupId>liferay.sample</groupId>
            <artifactId>liferay.sample.maven</artifactId>
            <version>1.0.0</version>
            <name>Liferay Maven Module Projects</name>
            <packaging>pom</packaging>

            <distributionManagement>
                <repository>
                    <id>liferay-releases</id>
                    <url>http://localhost:8081/nexus/content/repositories/liferay-releases</url>
                </repository>
            </distributionManagement>

            <modules>
                <module>portlet.ds</module>
            </modules>
        </project>

    The tags `<modelVersion>` through `<packaging>` are POM tags that are
    used frequently in parent POMs. Visit Maven's
    [POM Reference](https://maven.apache.org/pom.html) documentation for more
    information.

    The `<distributionManagement>` tag specifies the deployment repository for
    all module projects residing in the parent folder. You should include the
    repository's ID and URL. The above `distributionManagement` declaration is
    configured for the Liferay Nexus repository created in the
    [Creating a Maven Repository](/develop/tutorials/-/knowledge_base/7-0/creating-a-maven-repository)
    tutorial. That tutorial also created the `[USER_HOME]/.m2/settings.xml`,
    which specified the remote repository's ID, user name, and password. Both the
    parent POM and `settings.xml` file's repository declarations are required to
    deploy your modules to that remote repository.

    Finally, you must list the modules residing in the parent folder that you
    want deployed using the `<modules>` tag. The `portlet.ds` module is
    specified within that tag.

3.  Open the `portlet.ds` module's `pom.xml` file. If you did not download the
    `portlet.ds` module project Zip, you can reference its POM below. 

        <project
            xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
        >

            <modelVersion>4.0.0</modelVersion>
            <artifactId>portlet.ds</artifactId>
            <version>1.0.0</version>
            <packaging>jar</packaging>

            <parent>
                <groupId>liferay.sample</groupId>
                <artifactId>liferay.sample.maven</artifactId>
                <version>1.0.0</version>
                <relativePath>../pom.xml</relativePath>
            </parent>

            <dependencies>
                <dependency>
                    <groupId>javax.portlet</groupId>
                    <artifactId>portlet-api</artifactId>
                    <version>2.0</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>org.osgi</groupId>
                    <artifactId>org.osgi.service.component.annotations</artifactId>
                    <version>1.3.0</version>
                    <scope>provided</scope>
                </dependency>
            </dependencies>
        </project>

    The `portlet.ds` module's POM specifies its own attributes first, followed
    by the parent POM's attributes. Declaring the `<parent>` tag like above
    links the `portlet.ds` module to its parent POM, which is necessary to
    deploy to the remote repository. Then the module's dependencies are listed.
    These dependencies are downloaded from the Central Repository and
    installed to your local `.m2` repository when you package the `portlet.ds`
    module.

4.  Now that you've configured your parent POM and module POM, package your
    Maven project. Navigate to your module project (e.g., `project.ds`) in your
    command prompt and run the Maven package command:

        mvn package

    This downloads and installs all your module's dependencies and packages the
    project into a JAR file. Navigate to your module project's generated build
    folder (e.g., `/target`). You'll notice there is a newly generated JAR file.
    This is the artifact you'll deploy to your Nexus repository.

5.  Run Maven's deploy command to deploy your module project's artifact to your
    configured remote repository.

        mvn deploy

    Your console shows output from the artifact being deployed into your
    repository server.

To verify that your artifact is deployed, navigate to the Repositories page of
your Nexus server and select your repository. A window appears below showing
the Liferay artifact now deployed to your repository.

![Figure 1: Your repository server now provides access to your Liferay Maven artifacts.](../../../images/maven-verify-deployment.png)

Awesome! You can now share your Liferay module projects with anyone by deploying
them as artifacts to a remote repository!
