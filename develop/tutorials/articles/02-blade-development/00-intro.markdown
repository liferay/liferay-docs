# Creating Liferay Plugins/Bundles in the BLADE Environment [](id=creating-liferay-plugins-bundles-in-the-blade-environment)

Developing plugins for Liferay 7 is different than for previous versions of
Liferay. The old development paradigm had you deploying your plugins to the
server or servlet container where Liferay was also deployed, as separate
applications. Now, because of Liferay's module framework, your plugins live
alongside the modules that make up the core of Liferay (though Liferay
itself is not hosted in the framework). For more details, see our tutorial on
[Liferay's Module Framework](develop/tutorials/-/knowledge_base/7-0/getting-started/intro-to-liferay-module-framework).

This tutorial assumes you wish to develop bundles and deploy them into Liferay's
module framework. It also assumes that you don't want to use the Ant-based
Plugins SDK. The BLADE ([Bootstrap Liferay Advanced Developer Environments](https://github.com/rotty3000/blade)) project makes it easy to do this, freeing you to
integrate Liferay development into your current development workflow and tools.
If you're interested in developing "the Liferay way," you can learn to develop
bundles for Liferay 7 using Liferay's Plugins SDK in a [separate tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle). 

The BLADE project embraces the open source roots and ongoing culture of Liferay
by providing a repository of projects that are ready to be deployed to Liferay's
module framework. The plugins in the BLADE project aren't limited to one build
tool. The goal of the project is to provide project templates for
all the major build tools in common use so that Liferay development can start
quickly and easily. At the time of this writing, projects created with Gradle,
Maven, and Bndtools are included in the BLADE repository. Essentially, the
projects are templates that demonstrate Liferay 7's integration points. You can
develop inside the BLADE project environment itself or use it to create
standalone projects.

## Project Templates [](id=project-templates)

The projects are categorized based on the build tools supported.

- `maven`: these are set of Liferay projects that can be bootstrapped on to
  `maven` development environments

- `bndtools`: these are set of Liferay projects that can be bootstrapped on to
  `bndtools` based development environments

- `gradle`: these are set of Liferay projects that can be bootstrapped on to
  `gradle` based development environments

The projects also demonstrate how to use various frameworks like Blueprint,
Declarative Services (DS), and the OSGi API.

## Creating Bundles with BLADE [](id=creating-bundles-with-blade)

There are two approaches you can take when using the BLADE project to develop
and build your plugins. You can copy existing projects from the BLADE
repository, refactor and modify them to suit your use case, and then build
and deploy them to Liferay's module framework. If you like this approach, see
our tutorial on manually creating projects from BLADE templates. The other
approach is to install the command line BLADE Tools to build plugins from
scratch. The first step in this case is to [install Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools).

## Related Topics [](id=related-topics)

[Install BLADE Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

