# Using Liferay Maven Parent Plugin Projects [](id=using-liferay-maven-parent-plugin-projects)

Maven supports project inheritance. You can create a *parent* project that
contains properties that *child* projects inherit. This saves time, since you
don't need to specify those properties in each project. It also makes your
projects easier to maintain since if you have to change the value of a property
belonging to each of your projects, you only need to change it in the parent
project. If you develop more than one project, it makes sense to leverage
project inheritance so that all projects can share properties they have in
common. 

This tutorial demonstrates Maven inheritance with a Liferay Maven project that
includes a parent/child relationship. Whether or not you're going to leverage
Maven's project inheritance capabilities when you build your Liferay plugins
with Maven, the process is the same for creating any Liferay plugin with Maven's
Liferay artifacts. For more information on project inheritance, see Maven's
documentation at <http://maven.apache.org/pom.html#Inheritance>.

If you'll be creating multiple Liferay plugins, you should create a parent
project and then specify the general settings needed to build your plugins for
Liferay. The parent project functions similarly to the project root of the
Liferay Plugins SDK. Its `pom.xml` file specifies information to be used by any
child plugin projects that refer to it. Of course, you can always specify
information in each individual plugin's POM, but it's more convenient to use the
parent project's POM for sharing common information. 

Follow these steps to create a Liferay Maven parent plugin project: 

1.  Create a directory for your parent plugin project. 

2.  In your new parent plugin project directory, create a POM file named
    `pom.xml`.

    For example, here's POM XML code for a parent plugin project named
    `sample-parent-project`:

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
				<liferay.app.server.deploy.dir>
					${liferay.app.server.deploy.dir}
				</liferay.app.server.deploy.dir>
	
				<liferay.app.server.lib.global.dir>
					${liferay.app.server.lib.global.dir}
				</liferay.app.server.lib.global.dir>
	
				<liferay.app.server.portal.dir>
					${liferay.app.server.portal.dir}
				</liferay.app.server.portal.dir>
	
				<liferay.auto.deploy.dir>
					${liferay.auto.deploy.dir}
				</liferay.auto.deploy.dir>
	
				<liferay.version>
					${liferay.version}
				</liferay.version>
	
				<liferay.maven.plugin.version>
					${liferay.maven.plugin.version}
				</liferay.maven.plugin.version>
			</properties>
	
			<dependencies>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>portal-client</artifactId>
					<version>6.2.0-GA1</version>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>portal-impl</artifactId>
					<version>6.2.0-GA1</version>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>portal-pacl</artifactId>
					<version>6.2.0-GA1</version>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>portal-service</artifactId>
					<version>6.2.0-GA1</version>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>portal-web</artifactId>
					<version>6.2.0-GA1</version>
					<type>war</type>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>util-bridges</artifactId>
					<version>6.2.0-GA1</version>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>util-java</artifactId>
					<version>6.2.0-GA1</version>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>util-slf4j</artifactId>
					<version>6.2.0-GA1</version>
				</dependency>
				<dependency>
					<groupId>com.liferay.portal</groupId>
					<artifactId>util-taglib</artifactId>
					<version>6.2.0-GA1</version>
				</dependency>
			</dependencies>
	
		</project>

    The POM starts by specifying the model version that Maven supports, your
    project's [Maven coordinates](http://maven.apache.org/pom.html#Maven_Coordinates),
    your project's name, and your company's URL. 

    Next, the POM specifies some key Liferay property elements that your plugins
    require in order to be deployed to your Liferay portal. You can conveniently
    specify these values in a parent project for all of your child plugin
    projects to leverage. A plugin project can override any of its parent's
    properties by specifying the desired property explicitly in the child plugin
    project's POM. 

    <!--Is the correct term not child project, but module?-->

    If you use contents from the above example POM, make sure to replace
    each Liferay property value (e.g., replace
   `${liferay.app.server.deploy.dir}` and other dereferenced `liferay.*`
    properties) with the appropriate value based on your Liferay environment.

    Each key Liferay property is described below:

    - `liferay.app.server.deploy.dir`: Your app server's deployment directory. 
    - `liferay.app.server.lib.global.dir`: Your app server's global library
      directory. 
    - `liferay.app.server.portal.dir`: The path to Liferay's deployment
      directory on the app server. 
    - `liferay.auto.deploy.dir`: The path of your Liferay bundle's hot-deploy
      directory `deploy`. By specifying your Liferay instance's deploy
      directory in the POM, you're telling Maven exactly where to deploy your
      plugin artifacts. 
    - `liferay.maven.plugin.version`: The version of the Liferay Maven Plugin
      you are using. 
    - `liferay.version`: The version of Liferay you are using. 

    Here's an example of specifying these *properties* for Liferay bundled
    with Apache Tomcat in a directory `C:\liferay-portal-6.2`: 

		<properties>
			<liferay.app.server.deploy.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps
			</liferay.app.server.deploy.dir>

			<liferay.app.server.lib.global.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\lib\ext
			</liferay.app.server.lib.global.dir>

			<liferay.app.server.portal.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps\root
			</liferay.app.server.portal.dir>

			<liferay.auto.deploy.dir>
				C:\liferay-portal-6.2\deploy
			</liferay.auto.deploy.dir>

			<liferay.maven.plugin.version>
				6.2.0
			</liferay.maven.plugin.version>

			<liferay.version>
				6.2.0
			</liferay.version>
		</properties>

    You can also specify these key properties in your global or user
    `settings.xml` file. To learn more about this method, visit the *Configuring
    Your Liferay Maven Project* section of the
    [Using Maven From Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/using-maven-from-liferay-ide) 
    tutorial. 

    The Liferay plugins that you develop depend on several Liferay artifacts.
    You can include them in individual `dependency` elements within the POM's
    `dependencies` element. All of your parent project's modules (i.e., projects
    that refer to this parent) can leverage these dependencies. 

+$$$

**Note:** You could just as easily
    include such dependencies in the POM of each of your plugin projects, but
    specifying them in a parent project makes them accessible to child projects
    through inheritance. 

$$$

You've configured your parent plugin project. 
