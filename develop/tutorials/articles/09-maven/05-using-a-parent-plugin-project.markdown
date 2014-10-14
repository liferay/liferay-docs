# Using a Parent Plugin Project [](id=using-a-parent-plugin-project)

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

+$$$

**Note:** You could just as easily include such dependencies in the POM of each
of your plugin projects, but specifying them in a parent project makes them
accessible to child projects through inheritance. 

$$$
 
Now you're able to specify dependencies on required Liferay artifacts; let's
create a Liferay plugin project using the archetypes provided by Liferay. 
