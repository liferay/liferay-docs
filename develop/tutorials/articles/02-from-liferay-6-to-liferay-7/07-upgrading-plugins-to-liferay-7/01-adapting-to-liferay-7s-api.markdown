# Adapting to Liferay 7's API with the Code Upgrade Tool [](id=adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

The first step to upgrade an existing development to Liferay 7 is to adapt its
code to Liferay's API changes. Since Liferay 7 is a major version, it contains
some significant modifications to some APIs. Also, as part of the modularization
process many packages have been renamed to adopt a new standard. We have
carefully documented the API changes to explain what changed, how to adapt to
the change, and why the change was made).

To go above and beyond documenting the changes, we created the Code Upgrade
Tool: a tool that knows about these changes, analyzes your code, and suggests
how to adapt the code to the new APIs.

The Liferay Code Upgrade Tool (initially called the Migration Tool) is now a
part of Liferay IDE and Liferay Developer Studio. We recommend using this tool
to adapt any existing code to Liferay 7, even if you switch to another tool of
your preference once you are done upgrading to API changes.

In this tutorial, we show how to use this tool to adapt an existing plugin's
code to Liferay 7's API. As a prerequisite, we guide you in setting up your
existing traditional plugin in a Liferay 7 Plugins SDK, in Liferay IDE or
Liferay Developer Studio. After setup, you learn how to find and configure your
plugin's dependencies, so you can compile your code. And lastly, we demonstrate
how to use the Code Upgrade Tool to address upgrade issues. It's all
straightforward.

+$$$

Note: Even if Liferay IDE or Liferay Developer Studio or Eclipse are not your
favorite IDEs, we recommend that you use Liferay IDE (or Liferay Developer
Studio) and its Code Upgrade Tool to upgrade your code. Once you are done with
this task, you can go back to your preferred IDE and tool chain.

$$$

Let's start by setting up your plugin in a Plugins SDK in Liferay IDE or in
Liferay Developer Studio, for the best upgrade experience.

## Setup [](id=setup)

The Code Upgrade Tool is available in Liferay IDE and Liferay Developer Studio
(versions 3.0 and newer). So you should definitely upgrade your code with this
upgrade tool—you can always switch back to using your favorite IDE after you're
done.

Go ahead and upgrade your existing Liferay IDE or Liferay Developer Studio, or
download the latest version from the product site:

* [Liferay IDE](https://web.liferay.com/downloads/liferay-projects/liferay-ide)

* [Liferay Developer Studio](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)

Next, you need a Liferay 7 Plugins SDK in which to develop and deploy your
traditional plugin. The new SDK is available from the [downloads page](https://www.liferay.com/downloads).

In order to work with your plugin in a Liferay 7 Plugins SDK, you can either
move the plugin into a Liferay 7 Plugins SDK or unzip a Liferay 7 Plugins SDK
over the top of your existing SDK--that is, overwrite the existing SDK in which
your plugin resides.

Now that your plugin is situated in a Liferay 7 Plugins SDK, remember to set the
SDK's `app.server.type` and `app.server.parent.dir` build properties (i.e., in
your `build.[username].properties` file) to refer to your @product@
installation.

Here are the remaining steps for setting up your plugin project in Liferay
IDE/Developer Studio:

1.  Add a Liferay server and underlying runtime environment that targets your
    @product@ installation.

2.  Import your plugin project. *Selecting File &rarr; Import &rarr; Liferay &rarr; Liferay
    Plugins SDK Project* brings up a wizard in which you can import and
    configure your project.

Your existing plugin project, along with its Plugins SDK, appears in the IDE.
You're now ready to start upgrading the plugin to Liferay 7!

## Resolving Module Dependencies [](id=resolving-module-dependencies)

Now that you've imported your plugin project into Liferay IDE or Developer
Studio, the project probably reports undefined class errors and unresolved
symbol errors, with respect to Liferay classes it references. Each of these
classes has either been moved to a new package, moved to a new module that's not
in your project's classpath, replaced, or removed from the product.

In the case of **a class that's been replaced or removed** from the product, the
Code Upgrade Tool (discussed later) explains what happened to the class, what
you should do to handle the class's change, and why the change was made.

In the case of **a class that's been moved to a different package of a module
that's already in your classpath**, references to the class need to be resolved.
In other words, you need to update the class import. There are at least a couple
options for resolving such classes. The Code Upgrade Tool lists each moved class
in its report, but you have to address them one by one. As an alternative, IDEs
such as Eclipse (on which Liferay IDE and Developer Studio are based) provide a
feature for automatically resolving multiple classes at once. Such features
might resolve classes faster.

+$$$

**Tip**: Since Liferay IDE and Developer Studio are based on Eclipse, you can
generate imports to classes that are in your classpath by using the *Organize
Imports* keyboard sequence: *ctrl-shift-o*. If you have an import line that's in
error, comment out the line or remove it. Then press *ctrl-shift-o*. If there's
only one match for the class, an import statement is generated for it
automatically. Otherwise a wizard appears, letting you select the fully
qualified class name that matches the class. On selecting a matching fully
qualified class, Eclipse inserts an import statement for it.

$$$

What's left is the last case in which **a class that's been moved to a module
that's not yet in your classpath**. You'll need to resolve the module as a
dependency for your project. This requires identifying the module and specifying
your project's dependency on it.

The next subsections explain how to identify module dependencies and manage
them.

### Identifying Module Dependencies [](id=identifying-module-dependencies)

In Liferay Portal 6.2 and previous, all the platform APIs were provided in a
single JAR file: `portal-service.jar`. Many of these APIs have now been
extracted to independent modules, that can evolve and be versioned outside of
the platform kernel. You will need to work a little to find them but it will
repay later in simpler upgrades. For example, instead of having to check all of
Liferay’s APIs, each module will indicate clearly in the versioning scheme
whether any backwards incompatible change has been done. So you will only need
to check those in that situation that you use (if any). To learn more about the
benefits of this modularization, read the article [Benefits of Liferay 7 for Liferay 6 Developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers#modular-development-paradigm).

As part of the modularization, `portal-service.jar` has been renamed
appropriately to `portal-kernel.jar`, as it continues to hold the APIs that make
up the portal kernel.

![Figure 1: The portal-service JAR file has been refactored. Application APIs have been extracted from it and it's been renamed *portal-kernel*.](../../../images/from-liferay-6-portal-apis-before-after.png)

Each app module consists of a set of classes that are highly cohesive and
focused on a specific purpose, such as providing the app's API, implementation,
or web UI. The app modules are much easier to understand and fun to work with.
So let's track down the modules that now hold the classes your plugin
references.

We've created a table that maps each class moved from `portal-service.jar` to
its new module. The table includes each class' new package and the version of
the new module, at the time of @product@'s latest release. The table is in
reference article [Classes Moved from portal-service.jar](/web/guest/develop/reference/-/knowledge_base/7-0/classes-moved-from-portal-service-jar).

For each class, note the following information:

* *Module Symbolic Name*: The module's unique identifier
* *Module Version*: The module's version identifier
* *Package*: The class' Java package

You'll use this information to manage your plugin's dependency on these modules.

**IMPORTANT**: The module versions in the @product@ source code are currently
one micro-version higher than what's actually in that @product@ release. For
example, even though the source code for the Liferay Journal API module
specifies version 2.0.2, version 2.0.1 is what's bundled with the @product@
release. So **make sure to specify one micro-version lower for the module** than
what's listed in the reference article or @product@ source code.

Your plugin might reference classes that are in Liferay utility modules, such as
`util-java`, `util-bridges`, `util-taglib`, or `util-slf4j`. You can browse their
module artifacts on the site [MVNRepository](http://mvnrepository.com/artifact/com.liferay.portal).
All of versions of each module are available. When you click on a module
version, MVNRepository conveniently presents tabs for the Ivy, Maven, and Gradle
dependency elements to specify in your project. Note the Ivy dependency
information, as the next section demonstrates using it.

![Figure 2: The MVNRepository site conveniently presents module dependency elements for Ivy, Maven, Gradle, and more.](../../../images/from-liferay-6-mvnrepository-entry.png)

The following table shows the module symbolic name for each Liferay utility
module. Each symbolic name is linked to the module's location on the
MVNRepository site.

**Liferay Utility Module Symbolics Names**

  **Liferay Utility** |  &nbsp;**Module Symbolic Name** |
:---------------------- | :----------------------------------------- |
 util-bridges           |  [`com.liferay.util.bridges`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.bridges) |
 util-java               | [`com.liferay.util.java`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.java) |
 util-slf4j               | [`com.liferay.util.slf4j`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.slf4j) |
 util-taglib             | [`com.liferay.util.taglib`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.util.taglib) |
 
Once again, note the *module symbolic name* and *version* of the modules you
use. We'll cover setting module dependencies shortly.

+$$$

Note: The Plugins SDK makes `portal-kernel.jar` available in the classpath, as
the SDK formerly made `portal-service.jar` available. If you're using a Liferay
bundle (i.e., @product@ pre-installed on an app server), then the Liferay
utility modules are already in your classpath.

If you manually installed @product@ on your app server, the Liferay utility
modules might not be on your classpath. In that case, you should note the
dependency elements for the modules you need.

$$$

Now that you have the module symbolic names and versions, you can make the
modules available to your plugin project.

The modules your plugin uses must be available to it at compile time and run
time. Here are two options for resolving module dependencies in your traditional
plugin project:

**Option 1: Use Ivy**

**Option 2: Work with dependency JAR files manually**

The next sections explain and demonstrate these options. Let's first consider
using Ivy.

### Managing Dependencies with Ivy [](id=managing-dependencies-with-ivy)

Apache Ivy provides an elegant approach to managing dependencies. You declare
your dependencies an `ivy.xml` file in your plugin project's root folder. The
Plugins SDK's Ant tasks leverage the `ivy.xml` file, along with the Plugins
SDK's Ivy scripts, to download the specified modules and *their* dependencies,
and make them available to your plugin.

+$$$

**Note**: you can use Gradle or Maven in place of Ivy for dependency management;
but their use is out of the scope of this tutorial.

$$$

Here's an example dependency element for the Liferay Journal API version 2.0.1
module:

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="2.0.1" />

Each dependency includes the module's name, the name of its organization, and a
revision number.

Note, `"com.liferay"` is the `org` value for all Liferay modules.

At compile time, Ivy downloads the dependency JAR files to a cache folder so you
can compile against them. At deployment, the OSGi framework in @product@ takes
care of installing and registering the dependency modules for accessing at
runtime.

**IMPORTANT**: Remember that the module version numbers in the @product@ source
code are currently *one micro-version higher* than what's actually associated
with the @product@ release.

If your project doesn't already have an `ivy.xml` file, you can get one by
creating a new plugin project in Liferay IDE or Developer Studio and copying the
`ivy.xml` file it generates.

As an example, here are the contents from the Liferay 6.2 Knowledge Base portlet
application's `ivy.xml` file:

    <?xml version="1.0"?>

    <ivy-module
        version="2.0"
        xmlns:m2="http://ant.apache.org/ivy/maven"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://ant.apache.org/ivy/schemas/ivy.xsd"
    >
        <info module="knowledge-base-portlet" organisation="com.liferay">
            <extends extendType="configurations,description,info" location="${sdk.dir}/ivy.xml" module="com.liferay.sdk" organisation="com.liferay" revision="latest.integration" />
        </info>
    
        <dependencies defaultconf="default">
            <dependency org="com.liferay" name="com.liferay.markdown.converter" rev="1.0.2" />
        </dependencies>
    </ivy-module>

The Plugins SDK works with project Ivy files to store artifacts and make them
accessible to your plugin projects.

If you don't want to use Ivy, you can store dependency JARs within your plugin
project manually. We'll cover manual dependency management next.

### Managing Dependencies Manually [](id=managing-dependencies-manually)

Managing dependencies manually is also viable. If you're already managing
dependencies using Ivy or some other dependency management framework, then you
can skip this section.

Manual dependency management involves downloading dependency JAR files and
adding them to your project's `WEB-INF/lib` folder.

You can download Liferay JARs from the site MVNRepository:

    [http://mvnrepository.com/artifact/com.liferay](http://mvnrepository.com/artifact/com.liferay.portal)

The module's *symbolic name* and *version* are used as folder names in the Maven
repository and in the module artifact's name. For example, the Journal API
module's artifact JAR file is posted at this location:

    [http://mvnrepository.com/artifact/com.liferay/com.liferay.journal.api/2.0.1](http://mvnrepository.com/artifact/com.liferay/com.liferay.journal.api/2.0.1)

The module's JAR file is `com.liferay.journal.api-2.0.1.jar`

When managing dependencies manually, you must make sure to not *deploy* any OSGi
framework JARs or Liferay module JARs (e.g., *com.liferay.journal.api.jar*). If
you deploy them along with your plugin, they'll conflict with the JARs already
installed in the OSGi framework–identical JARs existing in two different
classloaders can cause class cast exceptions.

The easiest way to exclude such JARs from your plugin's deployment is to list
them in a `deploy-excludes` property in your plugin's
`liferay-plugin-package.properties`. You must otherwise remove the JARs manually
from the plugin WAR file.

To exclude JARs in your plugin's `liferay-plugin-package.properties` file, add
an entry like this:

    deploy-excludes=\
        **/WEB-INF/lib/[module-artifact.jar],\
        **/WEB-INF/lib/[another-module-artifact.jar]

Here's an example property from a *liferay-plugin-package.properties* that excludes OSGi framework JAR `osgi.core.jar` and Liferay app module JAR `com.liferay.journal.api.jar`:

    deploy-excludes=\
        **/WEB-INF/lib/com.liferay.portal.journal.api.jar,\
        **/WEB-INF/lib/org.osgi.core.jar

So how do you know what modules are already installed in @product@? If your
@product@ instance has a particular Liferay App Suite installed, then don't
deploy module JARs you know are in that App Suite. For example, if the Web
Experience Management App Suite is already installed (which is the case for a
@product@ bundle), then don't deploy Web Content module JARs such as
`com.liferay.journal.api.jar`.

A sure-fire way to check if a module your plugin uses is already installed in
@product@ is to search for it in @product@'s [App Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps).

Now that you've configured your plugin project's dependencies, you can use
Liferay's Code Upgrade Tool to adapt the rest of your plugin's code.

## Adapting to the API with the Code Upgrade Tool [](id=adapting-to-the-api-with-the-code-upgrade-tool)

The Code Upgrade Tool identifies areas in your code that need to be adapted to
changes in Liferay's APIs. As Liferay 7 was being developed, some of the changes
that needed to be made were unavoidable and resulted in problems that affect
plugin upgrades. They're commonly known as *breaking changes* and are captured
in @product@'s [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
document.

Liferay's Code Upgrade Tool finds the upgrade issues, describes them, and lists
them for you. You can either resolve each issue yourself or, if the tool enables
an auto-correct option for the issue, you can make the tool address it. As you
resolve the upgrade problems, you can check them off in the Code Upgrade Tool's
list.

To start the Code Upgrade Tool for processing breaking changes, follow these
steps:

1. In the *Project Explorer*, right-select your plugin project.

2. Select *Liferay &rarr; Find Liferay 7 breaking API changes…*
                                                                       
The following view appears.

![Figure 3: This view shows you where breaking changes affect your plugin. In addition, it gives you background information on each breaking change and explains how to adapt your plugin accordingly.](../../../images/from-liferay-6-upgrade-problems.png)

Upgrade problems are reported by file and line number. On selecting a problem
from the list, the following information about the problem appears: 

-  **Date**: The date the change was introduced to the product.

-  **JIRA Ticket**: Corresponding issue number.

-  **What Changed?**: Summary of the code change.

-  **How should I update my code?**: Instructions on how to adapt your code in
    response to the change. 
    
-  **Why was the change made?**: Reasons for the change.

Here's the Code Upgrade Tool workflow:

1.  Double-click a problem in the list, to open the affected file to the line of
    code related to the problem. 

2.  Correct the problem manually or automatically, if the Code Upgrade Tool
    offers an automatic fix. 

To verify whether there is an automatic correction, right-click on the problem
in the problem list. If there's a *Correct Automatically* option, you can click
it to apply that correction. 

![Figure 4: The Code Upgrade Tool provides auto-correction for some issues.](../../../images/from-liferay-6-correct-automatically.png)

When an auto correction is applied to the code, an information icon (i) appears
next to the line of code. The icon identifies the affected line of code and
displays the issue's title when you hover over the icon. 

![Figure 5: On applying auto-correction, an information icon appears next to the affected line of code](../../../images/from-liferay-6-upgrade-tool-at-line.png)

1. Mark the problem resolved.

2. If more unresolved exist, go back to step 1.

For more details on an issue, see its JIRA ticket by visiting
<https://issues.liferay.com>, entering the ticket number in the search box, and
clicking the search icon. The issue's description and comments provide relevant
information.

Resolving all of a plugin's reported migration problems makes for a great start
in adapting your plugin to Liferay 7.

## Summary [](id=summary)

Congratulations on completing the first step in upgrading your plugin to Liferay
7! Let's consider all that you've done.

You set up your plugin in a Liferay 7 Plugins SDK, imported it into Liferay IDE
or Liferay Developer Studio, and set up a Liferay 7 server in that IDE. Then,
you resolved dependencies on all the modules containing the classes your plugin
uses. As you did that, you fixed imports to classes you use. Finally, you used
Liferay's Code Upgrade Tool to hunt down and adapt to breaking API changes. Way
to go!

It's onward and upward with upgrading your traditional plugins on Liferay 7!

## Related Articles [](id=related-articles)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-0/invoking-local-services)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)

