# Using the Plugins SDK [](id=using-the-plugins-sdk)

This tutorial describes the Plugins SDK's directory structure and project Ant
targets, demonstrates how to create plugin projects, and shows how to deploy
them. 

By learning the SDK's directory structure, you'll know where to initiate
creating the different project types, know its build files, and know where
plugins archives are held for distribution. The Plugins SDK tour is up next. 

## Understanding the SDK's Structure

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-[version]/` - Plugins SDK root directory. 
    - `clients/` - client applications directory. 
    - `dist/` - archived plugins for distribution and deployment. 
    - `ext/` - Ext plugins directory. <!-- TODO See the [Ext Plugins
    tutorial](http://www.liferay.com) for details.--> 
    - `hooks/` - hook plugins directory. <!-- TODO See the [Hook Plugins
    tutorial](http://www.liferay.com) for details.--> 
    - `layouttpl/` - layout templates directory. <!-- TODO See the [Layout
    Templates tutorial](http://www.liferay.com) for details.-->  
    - `lib/` - commonly referenced libraries. 
    - `misc/` - development configuration files. Example, a source code
      formatting specification file. 
    - `portlets/` - portlet plugins directory. See the <!-- TODO [Portlets
    tutorial](http://www.liferay.com) for details. -->
    - `themes/` - themes plugins directory. See the <!-- TODO [Themes
    tutorial](http://www.liferay.com) for details.--> 
    - `tools/` - plugin templates and utilities. 
    - `webs/` - web plugins directory. 
    - `build.properties` - default SDK properties. 
    - `build.[username].properties` - (optional) override SDK properties. 
    - `build.xml` - contains targets to invoke in the SDK. 
    - `build-common.xml` - contains common targets and properties referenced
      throughout the SDK. 
    - `build-common-plugin.xml` - contains common targets and properties
      referenced by each plugin. 
    - `build-common-plugins.xml` - contains common targets and properties
      referenced by each plugin type. 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called *greeting-portlet* would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`. 

There's an Ant build file called `build.xml` in each of the plugins
directories. Here are some Ant targets you'll commonly use in developing your
plugins: 

- `build-service` - builds the service layer for a plugin, using Liferay Service
  Builder. 
- `clean` - cleans the residual files created by the invocations of the
  compilation, archiving, and deployment targets. 
- `compile` - compiles the plugin source code. 
- `deploy` - builds and deploys the plugin to your application server. 
- `format-source` - formats the source code per Liferay's source code
  guidelines, informing you of violations that must be addressed. See the
  [Development Sytle](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style#section-Development+Style-Format+Source)
  community wiki page for details. 
- `format-javadoc` - formats the Javadoc per Liferay's Javadoc guidelines. See
  the [Javadoc Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines)
  community wiki page for details.

Next, you'll learn how to create a plugin using Liferay SDK from a terminal
environment. 

## Creating Plugins with Liferay SDK

Liferay IDE makes it easy to create and deploy Liferay plugin projects since it
includes a bundled Liferay SDK. If you don't want to use Eclipse, you can still
leverage the SDK to create your Liferay plugins. 

Navigate to the `portlets` folder of your Plugins SDK and follow these steps: 

1.  On Linux and Mac OS X, enter

        ./create.sh [portlet name] "[portlet title]"

    For example,

        ./create.sh guest-book "Guest Book"

2.  On Windows, enter

        create.bat [portlet name] "[portlet title]"

    For example,

        create.bat guest-book "Guest Book"

Your terminal displays a BUILD SUCCESSFUL message from Ant, and a new project
containing your portlet plugin's directory structure is created inside of the
`portlets` folder in your Plugins SDK. This is where you'll work to implement
your own functionality. Note that the Plugins SDK automatically appends
"-portlet" to the project name when creating its directory if the project name
you specified doesn't end in "-portlet".

![Figure 1: The Plugins SDK's Ant targets enable you to create a new plugin project with a single command.](../../images/plugins-sdk-create-project.png)

+$$$

**Tip**: If you are using a source
control system such as Subversion, CVS, Mercurial, Git, etc., this would be a
good moment to do an initial check-in of your changes. After building the plugin
for deployment, several additional files will be generated that should *not* be
managed by a source control system. 

$$$

Lastly, consider some common practices used in developing plugins with the SDK.
The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you can keep those
portlets and themes in their own Plugins SDK project in your source code
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

<!-- Commenting out as I don't understand what's trying to be conveyed in this
paragraph. Jim

It's also possible to use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts of the project copy., but this process makes it possible to
create plugins with the Plugins SDK while conforming to the strict standards
some organizations have for their Java projects.
-->

Now you know how to create all types of Liferay plugin projects using Liferay's
foundational development tool: the Plugins SDK.

## Related Topics

[Deploying Plugins to a Local Portal Instance](/develop/tutorials/-/knowledge_base/6-2/deploying-plugins-to-a-local-portal-instance)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

