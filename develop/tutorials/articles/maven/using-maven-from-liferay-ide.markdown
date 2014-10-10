# Using Maven From Liferay IDE [](id=using-maven-from-liferay-ide)

Wouldn't it be nice if you could manage your Liferay Maven projects from Liferay
IDE? You can! Liferay IDE 2.0 introduces the Maven project configurator
(`m2e-liferay`), or the added support of configuring Maven projects as full
Liferay IDE projects. This tutorial explains what the Maven project
configurator does, how to install it, and how to install its dependencies. As
you read through it, you'll examine the structure of Liferay Maven projects and
explore some configuration options.

## Installing Maven Plugins for Liferay IDE

In order to properly support Maven projects in the IDE, you first need a
mechanism to recognize Maven projects as Liferay IDE projects. IDE projects are
recognized in Eclipse as faceted web projects that include the appropriate
Liferay plugin facet. Therefore, all IDE projects are also Eclipse web projects
(faceted projects with the web facet installed). In order for the IDE to recognize
the Maven project and for it to be able to leverage Java EE tooling features
(e.g., the *Servers* view) with the project, the project must be a flexible web
project. Liferay IDE relies on the following Eclipse plugins to provide this
capability:  

- `m2e-core` (Maven integration for Eclipse)
- `m2e-wtp` (Maven integration for WTP)

The `m2e-core` plugin provides the standard Maven tooling support for Eclipse.
It provides dependency resolution classpath management and an abstract project
configuration framework for adapters. The `m2e-wtp` plugin provides project
configuration mappings between the Maven models, described in the Maven
project's POMs, and the corresponding flexible web project supported in Eclipse.
This allows a Liferay Maven project to be recognized as a flexible web project.
The`m2e-liferay` plugin installation includes these plugins and installs them by
default. With these integration features in place, the only remaining
requirement is making sure that the `m2e-core` plugin can recognize the extra
lifecycle metadata mappings necessary for supporting Liferay's custom goals.
Let's break down the lifecycle mappings just a bit to get a better understanding
of what this means. 

Both Maven and Eclipse have their own standard build project lifecycles that are
independent from each other. For both to work together and run seamlessly within
Liferay IDE, a lifecycle mapping is required to link both lifecycles into one
combined lifecycle. Normally, this would have to be done manually by the user.
Fortunately, the `m2e-liferay` plugin combines the lifecycle metadata mapping
and Eclipse build lifecycles, to provide a seamless user experience. The
lifecycle mappings for your project can be viewed by right-clicking your project
and selecting *Properties* &rarr; *Maven* &rarr; *Lifecycle Mapping*. 

![Figure 1: You can view your project's lifecycle mappings.](../../images/maven-lifecycle-mapping.png)

When first installing Liferay IDE, the installation startup screen lets you
select whether you'd like to install the Maven plugins automatically. Don't
worry if you missed this during setup. To install the required Maven plugins,
navigate to *Help* &rarr; *Install New Software*. In the *Work with* field,
insert the following value:

    Liferay IDE repository - http://releases.liferay.com/tools/ide/latest/milestone/ 

![Figure 2: You can install the `m2e-liferay` plugin by searching for software on Liferay IDE's repository.](../../images/m2e-liferay-installation.png)

If the `m2e-liferay` plugin does not appear, then it's already installed. To
verify that it's installed, uncheck the *Hide items that are already installed*
checkbox and look for `m2e-liferay` in the list of installed plugins. Also, if
you'd like to view everything that is bundled with the `m2e-liferay` plugin,
uncheck the *Group items by category* checkbox. 

Awesome! The required Maven plugins are installed and your IDE instance is ready
to be mavenized! Next, you'll learn how to configure an existing Maven project.

## Configuring Your Liferay Maven Project

Now that your Liferay IDE is Maven-ready, you can examine the anatomy of a
Liferay Maven project. Note, you don't need to have an existing Liferay Maven
project to continue. However, if you'd like to create a new Liferay Maven
portlet project in the IDE before proceeding, you can do so by following
instructions in the the tutorial [Creating Liferay Portlets with
Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-liferay-portlets-with-maven).
Alternatively, you can import an existing Maven project by navigating to *File*
&rarr; *Import* &rarr; *Maven* and selecting the location of Maven project's
source code. 

+$$$

**Note:** Due to the lifecycle mapping
of Eclipse and Maven, it is unsafe to manually insert or overwrite the
`.classpath` file, `.project` file, and `.settings` folder. IDE automatically
generates these files when a project is imported and updates them appropriately.

$$$

The `m2e-core` plugin delegates your Liferay Maven plugin's project
configuration to the `m2e-liferay` project configurator. The `m2e-wtp` project
configurator then converts your Liferay WAR package into an Eclipse flexible web
project. Next, the `m2e-liferay` configurator looks for the Liferay Maven plugin
to be registered on the POM effective model for WAR type packages. If no Liferay
Maven plugin is configured on the effective POM for the project, project
configuration ceases. If the plugin is configured, the project configurator
validates your project's configuration, checking it's POM, parent POM, and the
project's properties. The configurator detects invalid properties and reports
them as errors in the IDE's POM editor. There are a list of key properties that
your project must specify in order for it to become a valid Liferay IDE project.
The [Using Maven Parent Plugin
Projects](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-maven-parent-plugin-projects)
tutorial identifies these properties and explains how they are used. 

You can specify these properties in either the Maven profile of the global
`settings.xml` file (recommended), in the user `settings.xml` file, in the
parent `pom.xml`, or in the project `pom.xml` directly. Each file is described
below:

- **Global `settings.xml`:** provides configuration for all plugins belonging to
  all users on a machine. This file resides in the
  `${MAVEN_HOME}/conf/settings.xml` directory.

- **User `settings.xml`:** provides configuration for all plugins belonging to a
  single user on a machine. This file resides in the
  `${USER_HOME}/.m2/settings.xml` directory.

- **Parent `pom.xml`:** provides configuration for all modules in the parent
  project.

- **Project `pom.xml`:** provides configuration for the single plugin project.

You can think of these choices as a hierarchy for how your Maven plugins receive
their properties. The project `pom.xml` overrides the parent `pom.xml`, the
parent `pom.xml` overrides the user `settings.xml` file, and the user
`settings.xml` file overrides the global `settings.xml` file. 

Note that if a *profile* is active from your user `settings.xml`, its values 
override your properties in a POM. If you'd like to specify the properties in a
POM, see the [Using Maven Parent Plugin
Projects](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-maven-parent-plugin-projects)
tutorial for more details. 

Here's an example of what a Maven profile looks like inside the `settings.xml`
file.

    <profiles>
        <profile>
            <id>sample</id>
                <properties>
                    <plugin.type>portlet</plugin.type>
                    <liferay.version>6.2.0</liferay.version>
                    <liferay.maven.plugin.version>6.2.0</liferay.maven.plugin.version>
                    <liferay.auto.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\deploy</liferay.auto.deploy.dir>
                    <liferay.app.server.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps</liferay.app.server.deploy.dir>
                    <liferay.app.server.lib.global.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\lib\ext</liferay.app.server.lib.global.dir>
                    <liferay.app.server.portal.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps\ROOT</liferay.app.server.portal.dir>
                </properties>
        </profile>
    </profiles>

Once you've configured a Maven profile in your user `settings.xml` file, you can
activate the profile by right-clicking on `[your project]` &rarr; *Properties*
&rarr; *Maven* and entering the profile IDs that supply the necessary settings
into the *Active Maven Profiles* text field. For example, to reference the
profile and properties we listed above, you'd enter *sample* for the Active
Maven Profile. Once you've specified all the values, the configurator
(`m2e-liferay`) validates the properties. If there are errors in the `pom.xml`
file, the configurator marks them in Liferay IDE's POM editor. If you fix a
project error, update the project to persist the fix by right-clicking `[your
project]` &rarr; *Maven* &rarr; *Update Project*. 

After your POM configuration meets the requirements, the configurator installs
the Liferay plugin facet and your Maven project is officially a Liferay IDE
project! 

You can execute Maven goals such as `liferay:build-lang` or `liferay:build-db`.
You can access and execute any of your project's Maven goals by right-clicking
`[your project]` &rarr; *Liferay* &rarr; *Maven* and select the goal you want to
execute. To learn more about Maven's build lifecycle and plugin goals, visit
Apache's [Build Lifecycle
Basics](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Build_Lifecycle_Basics)
guide. 

When working with your `pom.xml` file in the IDE, you'll notice that the editor has several
different modes. Each mode is described in the following listing:

- **Overview:** provides a graphical interface where you can add to and edit the
  `pom.xml` file.

- **Dependencies:** provides a graphical interface for adding and editing
  dependencies in your project, as well as modifying the `dependencyManagement`
  section of the `pom.xml` file.

- **Effective POM:** provides a read-only version of your project POM merged
  with its parent POM(s), `settings.xml`, and the settings in Eclipse for Maven.

- **Dependency Hierarchy:** provides a hierarchical view of project dependencies
  and an interactive listing of resolved dependencies.

- **pom.xml:** provides an editor for your POM's source XML.

The figure below, shows the `pom.xml` file editor and its modes. 

![Figure 3: Liferay IDE provides five interactive modes to help you edit and organize your POM.](../../images/pom-editor-features.png)

By taking advantage of these interactive modes, modifying and organizing your
POM and its dependencies has never been easier!
