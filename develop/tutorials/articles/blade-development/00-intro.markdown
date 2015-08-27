# Creating Liferay Plugins/Bundles in the BLADE Environment

Developing plugins for Liferay 7 is different than for previous versions of
Liferay. The old development paradigm had you deploying your plugins to the
server or servlet container where Liferay was also deployed, as a separate
application. Now, because of the Module Framework that Liferay hosts, your
plugins will live alongside many of the modules that make up the core of Liferay
(though Liferay itself is not hosted in the framework) For more details, see our
tutorial on [Liferay's Module Framework](develop/tutorials/-/knowledge_base/7-0/getting-started/intro-to-liferay-module-framework).
This tutorial assumes you wish to develop bundles and deploy them into Liferay's
Module Framework; such bundles are just JAR files consisting of Java classes,
additional resources (optionally), and a `MANIFEST.MF` file. It also assumes that
you're not going to use the ant-based Plugins SDK.

In a separate tutorial, you can learn to develop bundles for Liferay 7 using
Liferay's Plugins SDK. Another option you have is to use the [Bootstrap Liferay Advanced Developer Environments](https://github.com/rotty3000/blade) (BLADE, for short).
The BLADE project embraces the open source roots and ongoing culture of Liferay,
by providing a repository of projects that are ready to be deployed to Liferay's
module framework. The plugins in the BLADE project aren't limited to one build
tool. The goal of the project is to provide project templates for
all the major build tools in common use for Java projects so that Liferay
development can start quickly and easily. At the time of this writing, projects
created with Gradle, Maven, and Bndtools are included in the BLADE repository.
Essentially, the projects are templates that demonstrate Liferay 7's integration
points. You can develop inside the BLADE project environment itself, or use it to create
standalone projects.

## Project Templates

The projects are categorized based on the build tools supported.

- `maven`: these are set of Liferay projects that can be bootstrapped on to
  `maven` development environments

- `bndtools`: these are set of Liferay projects that can be bootstrapped on to
  `bndtools` based development environments

- `gradle`: these are set of Liferay projects that can be bootstrapped on to
  `gradle` based development environments

The projects also demonstrate how to use various frameworks like Blueprint,
Declarative Services(DS), and the OSGi API.

## Creating Bundles with BLADE

There are two basic approaches you can take when using the BLADE project to
develop and build your plugins. You can just copy existing projects from the
BLADE repository, refactor them and modify them to suit your use case, and then
build and deploy them to Liferay's module framework. If this is the approach
you choose, see our tutorial on manually creating projects from BLADE
templates. Alternatively, you can use Blade Tools to build plugins from
scratch, using a subcommand and specifying options to properly set up your
project during its creation. The first step in this case is to 
[install Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools).

## Related Topics

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

