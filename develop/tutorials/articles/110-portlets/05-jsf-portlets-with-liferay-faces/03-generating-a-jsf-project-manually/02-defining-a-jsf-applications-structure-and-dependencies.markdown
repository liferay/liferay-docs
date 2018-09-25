# Defining a JSF Application's Structure and Dependencies [](id=defining-a-jsf-applications-structure-and-dependencies)

JSF portlets are supported on Liferay Portal by using
[Liferay Faces Bridge](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-bridge).
Liferay Faces Bridge makes developing JSF portlets as similar as possible to JSF
web app development. 

You'll create a simple *Hello User* application that asks for the user's name
and then greets him or her with the name. You'll begin by creating the WAR-style
folder structure, and then you'll configure dependencies like Liferay Faces
Bridge.

1.  Create a WAR-style folder structure for your module.
    [Maven archetypes](https://web.liferay.com/web/neil.griffin/blog/-/blogs/new-maven-archetypes-for-jsf-portlets)
    are available to help you get started quickly. They set the default
    configuration for you and contain boilerplate code so you can skip the file
    creation steps and get started right away. For your JSF application, you'll
    set up the folder structure manually. Follow the folder structure outline
    below:

        - hello-user-jsf-portlet
            - src
                - main
                    - java
                    - resources
                    - webapp
                        - WEB-INF
                            - resources
                            - views

2.  Make sure your module specifies the dependencies necessary for a Liferay JSF
    application. For instance, you must always specify the Faces API, Faces
    Reference Implementation (Mojarra), and Liferay Faces Bridge as dependencies
    in a Liferay-compatible JSF application. Also, an important, but not
    required, dependency is the Log4j logging utility. This is highly
    recommended for development purposes because it logs DEBUG messages in the
    console. You'll configure the logging utility later.

    For an example build file, the `pom.xml` file used for the Maven based Hello
    User JSF application is below. All the dependencies described above are
    configured in the Hello User JSF application's `pom.xml` file.

        <?xml version="1.0"?>

        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.hello.user.jsf.portlet</artifactId>
            <packaging>war</packaging>
            <name>hello-user-jsf-portlet</name>
            <version>1.0-SNAPSHOT</version>
            <properties>
                <faces.api.version>2.2</faces.api.version>
                <liferay.faces.bridge.ext.version>5.0.3</liferay.faces.bridge.ext.version>
                <liferay.faces.bridge.version>4.1.2</liferay.faces.bridge.version>
                <mojarra.version>2.2.18</mojarra.version>
            </properties>
            <build>
                <plugins>
                    <plugin>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>3.3</version>
                        <configuration>
                            <encoding>UTF-8</encoding>
                            <source>1.8</source>
                            <target>1.8</target>
                        </configuration>
                    </plugin>
                    <plugin>
                        <artifactId>maven-war-plugin</artifactId>
                        <version>2.3</version>
                        <configuration>
                            <filteringDeploymentDescriptors>true</filteringDeploymentDescriptors>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
            <dependencies>
                <dependency>
                    <groupId>javax.faces</groupId>
                    <artifactId>javax.faces-api</artifactId>
                    <version>${faces.api.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>org.glassfish</groupId>
                    <artifactId>javax.faces</artifactId>
                    <version>${mojarra.version}</version>
                    <scope>runtime</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.ext</artifactId>
                    <version>${liferay.faces.bridge.ext.version}</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.impl</artifactId>
                    <version>${liferay.faces.bridge.version}</version>
                </dependency>
                <dependency>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                    <version>1.2.14</version>
                </dependency>
            </dependencies>
        </project>

    There are also two plugins the Hello User JSF application defined in its
    `pom.xml`:
    [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)
    and
    [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/).
    These two plugins are responsible for building and compiling the JSF
    application using Maven.

    There are several UI component suites that a JSF application can use,
    which include
    [*Liferay Faces Alloy*](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-alloy),
    [*PrimeFaces*](http://primefaces.org/),
    [*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf),
    and
    [*RichFaces*](http://richfaces.jboss.org/). Furthermore, you can take
    advantage of
    [*Liferay Faces Portal*](/develop/reference/-/knowledge_base/7-1/understanding-liferay-faces-portal)
    in order to use Liferay-specific utilities and UI components. These
    components can be used by specifying them as dependencies in your build
    file, as well.
    
Now that your build file is configured, you must define the JSF-specific
configurations for your application. These fall into two convenient categories:
general descriptors and Liferay descriptors. You'll start with creating the
necessary general descriptors next.