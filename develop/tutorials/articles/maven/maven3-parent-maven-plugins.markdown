### Using a Parent Plugin Project [](id=using-a-parent-plugin-project-liferay-portal-6-2-dev-guide-02-en)

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

We'll create our parent project and then specify the general settings needed to
build your plugins for Liferay. The parent project is similar to the project
root of the Liferay Plugins SDK. Its `pom.xml` file can specify information to
be used by any plugin projects that refer to it. You can always specify
information in each plugin's POM, but it's more convenient to use the parent
project's POM for sharing common information. 

Let's create a parent project named `sample-parent-project`. Start by creating a
new directory for your parent project. For this example, we'll name the
directory `sample-parent-project`. You can place the directory anywhere on your
file system. 

Next, create a POM file named `pom.xml` in your `sample-parent-project`
directory. Insert the following XML code into the POM:

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
project's [Maven
coordinates](http://maven.apache.org/pom.html#Maven_Coordinates), your
project's name, and your company's URL. 

Next, the POM specifies some key Liferay property elements that your plugins
require in order to be deployed to your Liferay portal. You can conveniently
specify these values in a parent project for all of your plugin projects to
leverage. A plugin project can override any of its parent's properties by
specifying the desired property explicitly in the child plugin project's POM. 

<!--Is the correct term not child project, but module?-->

Replace each Liferay property value (e.g., replace
`${liferay.app.server.deploy.dir}` and other dereferenced `liferay.*`
properties) with the appropriate value based on your Liferay environment. We've
described these key properties here: 

- `liferay.app.server.deploy.dir`: Your app server's deployment directory. 
- `liferay.app.server.lib.global.dir`: Your app server's global library
  directory. 
- `liferay.app.server.portal.dir`: The path to Liferay's deployment directory on
  the app server. 
- `liferay.auto.deploy.dir`: The path of your Liferay bundle's hot-deploy
  directory `deploy/`. By specifying your Liferay instance's deploy directory in
  the POM, you're telling Maven exactly where to deploy your plugin artifacts. 
- `liferay.maven.plugin.version`: The version of the Liferay Maven Plugin you
  are using. 
- `liferay.version`: The version of Liferay you are using. 

Here's an example where we've specified these *properties* for Liferay bundled
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
			6.2.0-GA1
		</liferay.maven.plugin.version>

		<liferay.version>
			6.2.0-GA1
		</liferay.version>
	</properties>

You can also specify these key properties in your Global or User `settings.xml`
file. To learn more about this method, visit the *Configuring Your Liferay Maven
Project* section.

The Liferay plugins you develop depend on several Liferay artifacts. We've
included them in individual `dependency` elements within the POM's
`dependencies` element. All of your parent project's modules (i.e., projects
that refer to this parent) can leverage these dependencies. 

---

![note](../../images/tip-pen-paper.png) **Note:** You could just as easily
include such dependencies in the POM of each of your plugin projects, but
specifying them in a parent project makes them accessible to child projects
through inheritance. 

---
 
Now that you specified your project's general information, your Liferay
environment properties, and the Liferay artifacts on which Liferay plugin
projects depend, let's create a plugin project using Liferay's archetypes. 
